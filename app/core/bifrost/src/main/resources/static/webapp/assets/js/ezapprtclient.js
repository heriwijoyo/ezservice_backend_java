var EzAppRTClient = {
    start: function(config) {
        EzAppRTClient.clientSessionId = config.sessionId;
        EzAppRTClient.onMessageHandler = config.onMessage;
        EzAppRTClient.wsSource = new WebSocket('wss://'+ config.host +':443/report');
        EzAppRTClient.wsSource.onmessage = function(e) {
            console.log(e.data);
            let data = JSON.parse(e.data);
            if (data.event === 'SESSION_AUTH_REQUIRED') {
                EzAppRTClient.sendMessage('PERFORM_AUTH_CLIENT', EzAppRTClient.clientSessionId);
            } else {
                EzAppRTClient.onMessageHandler(data);
            }
        }
    },
    requestData: function(dataSection) {
        EzAppRTClient.sendMessage('DATA_REQUEST', dataSection);
    },
    sendMessage: function(event, payload) {
        EzAppRTClient.wsSource.send(
            JSON.stringify({event: event, payload: payload})
        );
    },
    onMessageHandler: null,
    clientSessionId: null,
    wsSource: null
}