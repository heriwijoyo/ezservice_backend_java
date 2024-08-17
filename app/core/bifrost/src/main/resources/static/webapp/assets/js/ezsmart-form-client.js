var EzSmartFormClient = {
    fetchRemoteOptions: function(selectId, remoteUrl) {
        let postData = {
            sessionId : EzWebAppClient.getSessionId()
        };
        $.ajax({
            type: 'POST',
            url: remoteUrl,
            data: postData,
            success: function(data) {
                EzSmartFormClient.parseOptionData(selectId, data);
            },
            error: function(xhr) {
                console.log('EzSmartFormClient::error fetch data for id: '+ selectId +', remoteUrl: '+ remoteUrl);
            }
        });
    },
    parseOptionData: function(selectId, data) {
        let optionHtml = '';
        for (i = 0; i < data.length; i++) {
            optionHtml += '<option value="'+ data[i].value +'">'+ data[i].label +'</option>';
        }
        $('#'+selectId).html(optionHtml);
    }
}