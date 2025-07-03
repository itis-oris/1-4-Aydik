const phoneInput = document.getElementById('phone');

phoneInput.addEventListener('input', (event) => {
    const input = event.target;
    const rawValue = input.value.replace(/\D/g, ''); // Удаляем все нечисловые символы
    const cursorPosition = input.selectionStart; // Текущая позиция курсора до форматирования

    // Форматируем номер
    const formattedValue = formatPhoneNumber(rawValue);

    // Устанавливаем новое значение поля
    input.value = formattedValue;

    // Корректируем положение курсора
    const newCursorPosition = getNewCursorPosition(cursorPosition, rawValue, formattedValue);
    input.setSelectionRange(newCursorPosition, newCursorPosition);
});

function formatPhoneNumber(value) {
    // Форматируем только цифры в формат +7 (XXX) XXX-XX-XX
    if (!value) return '';
    let formatted = '+7 ';
    if (value.length > 1) {
        formatted += '(' + value.slice(1, 4);
    }
    if (value.length > 4) {
        formatted += ') ' + value.slice(4, 7);
    }
    if (value.length > 7) {
        formatted += '-' + value.slice(7, 9);
    }
    if (value.length > 9) {
        formatted += '-' + value.slice(9, 11);
    }
    return formatted;
}

function getNewCursorPosition(cursorPosition, rawValue, formattedValue) {
    // Рассчитываем позицию курсора после форматирования
    const cursorMap = buildCursorMap(rawValue, formattedValue);
    return cursorMap[cursorPosition] || formattedValue.length;
}

function buildCursorMap(rawValue, formattedValue) {
    // Карта соответствия индексов "чистого" значения и форматированного
    const cursorMap = [];
    let rawIndex = 0;

    for (let i = 0; i < formattedValue.length; i++) {
        if (/\d/.test(formattedValue[i])) {
            cursorMap[rawIndex] = i;
            rawIndex++;
        }
    }

    // Заполняем оставшиеся индексы для корректной работы при вводе/удалении
    for (let i = rawIndex; i <= rawValue.length; i++) {
        cursorMap[i] = formattedValue.length;
    }

    return cursorMap;
}
