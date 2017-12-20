function check() {
    var list = document.getElementById("delete").value;
                     var html = new XMLHttpRequest();
                    html.open('GET', "http://localhost:8081/ListFile?deleteFileName="+list);
                    html.send();
                    setTimeout(function(){window.location.reload(true);},100);

    }
function download() {
    setTimeout(function(){window.location.reload(true);},100);

}
function sen() {
    var str = document.getElementById("FILE").value;
    var time = document.getElementById("time").value;
    var status = document.getElementById("status");
    var names = document.getElementById("polis").value;
    var name = "all"
    var ittime = new Date();
    var itstime = ittime.getTime()
    if(str.length == name.length) {
        var count = 0;
        for (var j = 0; j <str.length; j++) {
            var g = str.charAt(j)
            var s = name.charAt(j)
            if (s == g)
                count++
        }
    }
    if(count != 3) {
    status.value = "http://localhost:8081//GD?file="+str+"&time="+time+"&ittime="+itstime;
    }

    else {
        status.value = "http://localhost:8081//GCatalog?file=" + names + "&time=" + time + "&ittime=" + itstime;
    }


}

