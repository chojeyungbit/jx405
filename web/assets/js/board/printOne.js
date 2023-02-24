let initPage = () => {
    let temp = new URLSearchParams(window.location.search).get("id");
    let data;
    data = {"id": temp}

    $.ajax({
        url: "/board/printOne",
        method: "GET",
        data: data,
        success: (message) => {
            console.log(message);
            let result = JSON.parse(message);
            if (result.status == 'success') {
                let data = JSON.parse(result.data);
                printData(data);
            } else {
                console.log(data);
                Swal.fire({
                    title: "!!! ERROR !!!",
                    text: result.message,
                    icon: "error"
                }).then(() => {
                    location.href = result.nextPath;
                })
            }
        }
    });
}

function printData(data) {
    $('#td-id').text(data.id + "번");
    $('#td-title').text(data.title);
    $('#td-writer').text(data.writerNickname);
    $('#td-entry-date').text(data.entryDate);
    $('#td-modify-date').text(data.modifyDate);
    $('#td-content').append(data.content);
    if (data.isOwned == true) {
        let tr = $(document.createElement("tr"));
        let td = $(document.createElement("td")).attr("colspan", "2");

        let btnUpdate = $(document.createElement("div")).addClass("btn btn-success w-50").text("수정").click(() => {
            location.href = "/board/update.jsp?id=" + data.id;
        });

        let btnDelete = $(document.createElement("div")).addClass("btn btn-danger w-50").text("삭제").click(() => {
            let data = {
                "id": data.id
            };
            $.ajax({
                url: "/board/delete",
                method: "get",
                data: data,
                success: (message) => {
                    let response = JSON.parse(message);
                    if (response.status == 'success') {
                        Swal.fire({title: "!!! 삭제 성공 !!!", text: "성공적으로 글이 삭제되었습니다."}).then(() => {
                            location.href = "/board/printList.jsp?id=1";
                        })
                    } else {
                        Swal.fire({title: "!!! 삭제 실패 !!!", text: "문제가 발생하여 글을 삭제하지 못하였습니다."});
                    }
                }
            })
        });

        $(td).append(btnUpdate);
        $(td).append(btnDelete);

        $(tr).append(td);
        $('#table-board').append(tr);
    }
}

















