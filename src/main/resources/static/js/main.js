document.addEventListener('DOMContentLoaded', function() {
    const reportTypeSelect = document.getElementById('reportType');
    const instructionsText = document.getElementById('instructions-text');
    const dynamicFields = document.querySelectorAll('.dynamic-report-field');
    const reportsThatShowFields = ['CHAMADA', 'LISTA_ESPERA'];

    function updateReportSpecificUI() {
        const selectedOption = reportTypeSelect.options[reportTypeSelect.selectedIndex];
        if (selectedOption) {
            const instructions = selectedOption.getAttribute('data-instructions');
            instructionsText.textContent = instructions;
        }

        const selectedReportType = reportTypeSelect.value;
        const showFields = reportsThatShowFields.includes(selectedReportType);

        dynamicFields.forEach(field => {
            field.style.display = showFields ? 'block' : 'none';

            const input = field.querySelector('input, textarea');
            if (input) {
                if (showFields) {
                    input.setAttribute('required', 'required');
                } else {
                    input.removeAttribute('required');
                }
            }
        });
    }

    reportTypeSelect.addEventListener('change', updateReportSpecificUI);

    updateReportSpecificUI();
});