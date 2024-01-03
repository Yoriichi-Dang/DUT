$(document).ready(function() {
    $('.menu-item').click(function() {
        // Kiểm tra xem có thẻ ul bên trong hay không
        if ($(this).children('.menu-sub').length === 0) {
            // Nếu không có thẻ ul, thêm class 'active'
            $(this).addClass('active');
        } else {
            // Nếu có thẻ ul, thêm class 'active' và 'open', loại bỏ 'active' và 'open' từ các thẻ li khác
            $(this).toggleClass('active open').siblings().removeClass('active open');
        }
    });
});