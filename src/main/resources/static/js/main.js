document.addEventListener('DOMContentLoaded', function() {
    // Elementos do DOM
    const reportTypeSelect = document.getElementById('reportType');
    const selectionProcessSelect = document.getElementById('selectionProcess');
    const instructionsText = document.getElementById('instructions-text');
    const requiredFieldsList = document.getElementById('required-fields-list');
    const processLogoUrlInput = document.getElementById('processLogoUrl');
    const validateCsvBtn = document.getElementById('validateCsvBtn');
    const csvFileInput = document.getElementById('csvFile');
    const messageContainer = document.getElementById('message-container');

    const processLogoMap = {
        "+Enem": "https://raw.githubusercontent.com/alerario/report/master/maisenem.png",
        "Vestibular": "https://raw.githubusercontent.com/alerario/report/master/vestibular.png",
        "PSS": "https://raw.githubusercontent.com/alerario/report/master/pss.png",
        "SiSU": "https://raw.githubusercontent.com/alerario/report/master/sisu.png"
    };

    // Função para atualizar a UI baseada no Tipo de Relatório
    function updateReportSpecificUI() {
        const selectedOption = reportTypeSelect.options[reportTypeSelect.selectedIndex];
        if (!selectedOption) return;

        // 1. Atualiza as instruções
        instructionsText.textContent = selectedOption.getAttribute('data-instructions');

        // 2. Atualiza a lista de campos obrigatórios
        const headers = selectedOption.getAttribute('data-required-headers').split(';');
        requiredFieldsList.innerHTML = ''; // Limpa a lista anterior
        headers.forEach(header => {
            const li = document.createElement('li');
            li.textContent = header;
            requiredFieldsList.appendChild(li);
        });
    }

    // Função para atualizar a logo baseada no Processo Seletivo
    function updateProcessLogo() {
        const selectedProcess = selectionProcessSelect.value;
        processLogoUrlInput.value = processLogoMap[selectedProcess] || '';
    }

    // Função para validar o cabeçalho do CSV
    function validateCsvHeader() {
        const file = csvFileInput.files[0];
        if (!file) {
            displayMessage('Por favor, selecione um arquivo CSV primeiro.', 'danger');
            return;
        }

        const selectedOption = reportTypeSelect.options[reportTypeSelect.selectedIndex];
        const requiredHeaders = new Set(selectedOption.getAttribute('data-required-headers').split(';'));

        const reader = new FileReader();
        reader.onload = function(e) {
            // Lê apenas a primeira linha do arquivo
            const firstLine = e.target.result.split('\n')[0].trim();
            // Assume delimitador ; e codificação ISO-8859-1 (a leitura de texto aqui pode não ser perfeita para todos os charsets, mas para validação de header deve funcionar)
            const actualHeaders = new Set(firstLine.split(';'));

            const missingHeaders = [];
            requiredHeaders.forEach(required => {
                if (!actualHeaders.has(required)) {
                    missingHeaders.push(required);
                }
            });

            if (missingHeaders.length === 0) {
                displayMessage('Sucesso! O cabeçalho do CSV contém todos os campos obrigatórios.', 'success');
            } else {
                displayMessage(`Erro: Campos obrigatórios não encontrados no CSV: ${missingHeaders.join(', ')}`, 'danger');
            }
        };

        reader.onerror = function() {
            displayMessage('Erro ao ler o arquivo.', 'danger');
        };

        // Lê o arquivo como texto (para inspecionar o cabeçalho)
        reader.readAsText(file, 'ISO-8859-1');
    }

    // Função para exibir mensagens
    function displayMessage(text, type) {
        messageContainer.innerHTML = `<div class="alert alert-${type}">${text}</div>`;
    }

    // Event Listeners
    reportTypeSelect.addEventListener('change', updateReportSpecificUI);
    selectionProcessSelect.addEventListener('change', updateProcessLogo);
    validateCsvBtn.addEventListener('click', validateCsvHeader);

    // Inicializa a UI na carga da página
    updateReportSpecificUI();
    updateProcessLogo();
});