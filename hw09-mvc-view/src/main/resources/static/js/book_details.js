window.onload = function () {
    const dialog = document.querySelector("#delete_dialog");
    const showButton = document.querySelector("#delete_button");
    const closeButton = document.querySelector("#delete_dialog_cancel");
    const yesButton = document.querySelector("#delete_dialog_yes");

    showButton.addEventListener("click", () => {
        dialog.showModal();
    });

    closeButton.addEventListener("click", () => {
        dialog.close();
    });
}
