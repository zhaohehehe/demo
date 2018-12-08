var socket = null;

function log(message) {
    var console = document.getElementById('console');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    console.appendChild(p);
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;
}
function connect(messarea) {
    var protocol = 'ws:';
    var wlhost="172.16.124.25:8888";
	var normalReg=/.*:\d+/gi;
	var host=normalReg.exec(wlhost);
	if(host == null){
		host=wlhost + ":80";
	}
	if (window.location.protocol != 'http:') {
    	protocol = 'wss:'
    }
	if ('WebSocket' in window) {
		try{
			socket = new WebSocket(protocol + host);
		}catch(e){
			alert(e);
		}
	} else if ('MozWebSocket' in window) {
		try{
			socket = new WebSocket(protocol + host);
		}catch(e){
			alert(e);
		}
	} else {
		alert('WebSocket is not supported by this browser.');
		return;
	}
	
    $(messarea).text("正在连接服务器....");
    
    socket.onopen = function (event) {
        addText("连接成功！",messarea);
        log('Info: WebSocket connection opened.');
    };
    socket.onmessage = function (event) {
    	addText(event.data, messarea);
    	log('Received: ' + event.data);
    };
    
    socket.onclose = function (event) {
        addText("连接断开！", messarea);
        log('Info: WebSocket connection closed, Code: ' + event.code
				+ (event.reason == "" ? "" : ", Reason: " + event.reason));
    };
};

function disconnect() {
	if (socket != null) {
		socket.close();
		socket = null;
	}
}

function addText(msg, messarea) {
	
    $(messarea).val($(messarea).val() + "\n" + msg);
    $(messarea).scrollTop($(messarea)[0].scrollHeight);
    
};