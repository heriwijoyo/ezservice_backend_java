var EzSmartFormClient = {
    detailId: EzWebAppClient.getUrlParameter('id'),
    fetchAndParseRemoteOption: function(selectId, remoteUrl) {
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
    },
    parseSelectedOption: function(selectId, selected) {
        let data = EzSmartFormClient.data[selectId];
        let optionHtml = '';
        var htmlSelected = '';
        for (i = 0; i < data.length; i++) {
            if (data[i].value === selected) {
                htmlSelected = ' selected';
            }
            optionHtml += '<option value="'+ data[i].value +'"'+ htmlSelected +'>'+ data[i].label +'</option>';
        }
        $('#'+selectId).html(optionHtml);
    },
    validateDetailId: function() {
        if (EzWebAppClient.isBlank(EzSmartFormClient.detailId)) {
            alert('Invalid Detail ID');
            EzSmartFormClient.goToPageDefault();
        }
    },
    goToPageDefault: function() {
        window.location.replace(EzSmartFormClientStarter.pageDefault);
    },
    fetchAndStoreRemoteOption: function(selectId, remoteUrl) {
        let postData = {
            sessionId : EzWebAppClient.getSessionId()
        };
        $.ajax({
            type: 'POST',
            url: remoteUrl,
            data: postData,
            success: function(data) {
                EzSmartFormClient.data[selectId] = data;
            },
            error: function(xhr) {
                console.log('EzSmartFormClient::error fetch data for id: '+ selectId +', remoteUrl: '+ remoteUrl);
            }
        });
    },
    data: []
}