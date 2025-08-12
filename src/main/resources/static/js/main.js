document.addEventListener('DOMContentLoaded', function () {
    // Seletores dos elementos do formulário
    const selectionProcessSelect = document.getElementById('selectionProcess');
    const reportTypeSelect = document.getElementById('reportType');
    const instructionsText = document.getElementById('instructions-text');
    const requiredFieldsList = document.getElementById('required-fields-list');

    // Campos a serem preenchidos automaticamente
    const reportTitleInput = document.getElementById('reportTitle');
    const reportSubtitleTextarea = document.getElementById('reportSubtitle');
    const explanationTextTextarea = document.getElementById('explanationText');
    const footerTextTextarea = document.getElementById('footerText');
    const qrCodeUrlInput = document.getElementById('qrCodeUrl');

    /**
     * Atualiza todos os campos dinâmicos do formulário com base
     * no processo seletivo e tipo de relatório selecionados.
     */
    function updateDynamicFormFields() {
        const selectedReportOption = reportTypeSelect.options[reportTypeSelect.selectedIndex];
        if (!selectedReportOption) return;

        // --- 1. Atualizar Instruções e Campos Obrigatórios (lógica existente) ---
        const instructions = selectedReportOption.dataset.instructions || 'Selecione um tipo de relatório para ver as instruções.';
        const requiredHeaders = selectedReportOption.dataset.requiredHeaders || '';

        instructionsText.textContent = instructions;
        requiredFieldsList.innerHTML = ''; // Limpa a lista anterior
        requiredHeaders.split(';').forEach(header => {
            if (header) {
                const li = document.createElement('li');
                li.textContent = header;
                requiredFieldsList.appendChild(li);
            }
        });

        // --- 2. Preencher campos do relatório com textos padrão ---
        const selectedProcessText = selectionProcessSelect.options[selectionProcessSelect.selectedIndex].text;

        const titleTemplate = selectedReportOption.dataset.defaultTitleTemplate || '';
        const subtitle = selectedReportOption.dataset.defaultSubtitle || '';
        const explanation = selectedReportOption.dataset.defaultExplanation || '';
        const qrCodeUrl = selectedReportOption.dataset.defaultQrCodeUrl || '';
        const footerText = selectedReportOption.dataset.defaultFooterText || '';

        // Formata o título com o nome do processo seletivo
        reportTitleInput.value = titleTemplate.replace('%s', selectedProcessText.toUpperCase());

        // Preenche os outros campos
        reportSubtitleTextarea.value = subtitle;
        explanationTextTextarea.value = explanation;
        qrCodeUrlInput.value = qrCodeUrl;
        footerTextTextarea.value = footerText;
    }

    // Adiciona os listeners de eventos para os seletores
    reportTypeSelect.addEventListener('change', updateDynamicFormFields);
    selectionProcessSelect.addEventListener('change', updateDynamicFormFields);

    // Chama a função uma vez no carregamento da página para inicializar o formulário
    updateDynamicFormFields();

    // --- Lógica do botão de validação do CSV ---
    const validateBtn = document.getElementById('validateCsvBtn');
    const csvInput = document.getElementById('csvFile');
    const validationContainer = document.getElementById('validation-message-container');

    /**
     * Exibe uma mensagem de validação na tela.
     * @param {string} message - A mensagem a ser exibida (pode conter HTML).
     * @param {boolean} isSuccess - True para mensagem de sucesso (verde), false para erro (vermelho).
     */
    function displayValidationMessage(message, isSuccess) {
        validationContainer.innerHTML = ''; // Limpa mensagens anteriores
        const messageDiv = document.createElement('div');
        messageDiv.className = `alert ${isSuccess ? 'alert-success' : 'alert-danger'}`;
        messageDiv.innerHTML = message;
        validationContainer.appendChild(messageDiv);
    }

    if (validateBtn) {
        validateBtn.addEventListener('click', function() {
            validationContainer.innerHTML = ''; // Limpa ao clicar

            if (csvInput.files.length === 0) {
                displayValidationMessage('Por favor, selecione um arquivo CSV para verificação.', false);
                return;
            }

            const file = csvInput.files[0];
            const selectedReportOption = reportTypeSelect.options[reportTypeSelect.selectedIndex];
            const requiredHeadersRaw = selectedReportOption.dataset.requiredHeaders;
            const requiredHeaders = (requiredHeadersRaw || '').split(';').map(h => h.trim()).filter(Boolean);

            if (requiredHeaders.length === 0) {
                displayValidationMessage('<strong>Sucesso!</strong> Este tipo de relatório não exige colunas específicas para verificação.', true);
                return;
            }

            const reader = new FileReader();

            reader.onload = function(e) {
                try {
                    const content = e.target.result;
                    const firstLine = content.split(/[\r\n]+/)[0].trim().replace(/^\uFEFF/, '');
                    const fileHeaders = firstLine.split(';').map(h => h.trim().replace(/^"|"$/g, ''));
                    const fileHeadersSet = new Set(fileHeaders);
                    const missingHeaders = requiredHeaders.filter(header => !fileHeadersSet.has(header));

                    if (missingHeaders.length === 0) {
                        displayValidationMessage('<strong>Sucesso!</strong> O arquivo CSV contém todos os campos obrigatórios.', true);
                    } else {
                        let errorMessage = '<strong>Erro:</strong> O arquivo CSV não contém os seguintes campos obrigatórios:<ul>';
                        missingHeaders.forEach(h => {
                            errorMessage += `<li>${h}</li>`;
                        });
                        errorMessage += '</ul>';
                        displayValidationMessage(errorMessage, false);
                    }
                } catch (readError) {
                    displayValidationMessage(`Ocorreu um erro ao processar o arquivo: ${readError.message}`, false);
                }
            };

            reader.onerror = function() {
                displayValidationMessage('Não foi possível ler o arquivo. Verifique as permissões ou se o arquivo não está corrompido.', false);
            };

            // Usa ISO-8859-1 (Latin-1) para manter consistência com as instruções de exportação do sistema.
            reader.readAsText(file, 'UTF-8');
        });
    }
});