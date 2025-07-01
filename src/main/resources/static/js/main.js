// src/main/resources/static/js/main.js
document.addEventListener('DOMContentLoaded', function() {
    const reportTypeSelect = document.getElementById('reportType');
    const instructionsText = document.getElementById('instructions-text');

    function updateInstructions() {
        const selectedOption = reportTypeSelect.options[reportTypeSelect.selectedIndex];
        if (selectedOption) {
            // Pega o texto do atributo data-instructions
            const instructions = selectedOption.getAttribute('data-instructions');
            instructionsText.textContent = instructions;
        }
    }

    // Atualiza as instruções quando a seleção muda
    reportTypeSelect.addEventListener('change', updateInstructions);

    // Atualiza as instruções no carregamento inicial da página
    updateInstructions();
});