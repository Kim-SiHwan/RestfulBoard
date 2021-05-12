var boardManager = (function () {
    var getAll = function (callback) {
        $.ajax({
            type: 'get',
            url: '/boards/',
            dataType: 'json',
            success: callback
        });
    };

    var getOne = function (obj,callback) {
        $.ajax({
            type:'get',
            url: '/boards/'+obj.boardId,
            dataType: 'json',
            success : callback

        });
    };

    var add = function (obj,callback) {
        $.ajax({
            type: 'post',
            url: '/boards/',
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: 'json',
            success: callback

        });

    };

    var remove = function (obj, callback) {

        $.ajax({
            type: 'delete',
            url: '/boards/' + obj.boardId,
            dataType: 'json',
            success: callback
        });

    };

    return {
        getAll: getAll,
        getOne: getOne,
        add: add,
        remove: remove
    }

})();
