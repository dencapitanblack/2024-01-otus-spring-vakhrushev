window.onload = function () {
    const dialog = document.querySelector("#delete_dialog");
    const showButton = document.querySelector("#delete_button");
    const closeButton = document.querySelector("#delete_dialog_cancel");

    showButton.addEventListener("click", () => {
        dialog.showModal();
    });

    closeButton.addEventListener("click", () => {
        dialog.close();
    });
}
