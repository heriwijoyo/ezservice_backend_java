var EzWebAppViewHelper = {
    start: function() {
        $('#common-modal-close').click(function(){
            $('#common-modal').removeClass('show');
            $('#common-modal').addClass('fade');
            EzWebAppViewHelper.onModalClose();
        });
    },
    showModalAlert: function(title,message,onClose) {
        EzWebAppViewHelper.onCustomModalClose = onClose;
        $('#common-modal-title').html(title);
        $('#common-modal-message').html(message);
        $('#common-modal').removeClass('fade');
        $('#common-modal').addClass('show');
    },
    onModalClose: function() {
        $('#common-modal').removeClass('show');
        $('#common-modal').addClass('fade');
        if (EzWebAppViewHelper.onCustomModalClose !== undefined) {
            EzWebAppViewHelper.onCustomModalClose();
        }
    },
    onCustomModalClose: function() {},
};
var EzApiUrl = {
    GET_APP_DATA: 'api/getAppData.json',
    GET_DASHBOARD_DATA: 'api/getDashboardData.json',
    GET_APP_GALLERY: 'api/getAppGallery.json'
};
var EzWebAppBizService = {
    handleAppData: function(response) {
        $('#app-info-name').html(response.data.orgName);
        $('#user-info-name').html('<b>'+response.data.memberName+'</b>');
        $('#user-info-phone').html('<b>'+response.data.memberPhone+'</b>');

        for (let i = 0; i < response.data.menu.length; i++) {
            let menu = $.parseHTML(EzWebAppHTMLTemplate.leftMenu);
            $(menu).find('#left-menu-url').attr('href', response.data.menu[i].menuUrl);
            $(menu).find('#left-menu-name').html(response.data.menu[i].menuName);
            $(menu).find('#left-menu-icon').html(response.data.menu[i].menuIcon);
            $('#left-menu-container').append(menu);
        }
        if (EzWebAppClient.onReadyHandler!==undefined) {
            EzWebAppClient.onReadyHandler();
        }
    }
};
var EzWebAppClient = {
    sessionIdCookieName: 'EzWebappCookieSessionId',
    modalScene: '',
    start: function() {
        EzWebAppViewHelper.start();
        EzWebAppClient.callApiService(EzApiUrl.GET_APP_DATA, {});
    },
    onReady: function(handler) {
        EzWebAppClient.onReadyHandler = handler;
    },
    onPrev: function(handler) {
        EzWebAppClient.onPrevHandler = handler;
    },
    prev: function() {
        if (EzWebAppClient.onPrevHandler!==undefined) {
            EzWebAppClient.onPrevHandler();
        }
    },
    onNext: function(handler) {
        EzWebAppClient.onNextHandler = handler;
    },
    next: function() {
        if (EzWebAppClient.onNextHandler!==undefined) {
            EzWebAppClient.onNextHandler();
        }
    },
    onApiBizSuccess: function(handler) {
        EzWebAppClient.apiBizSuccessHandler = handler;
    },
    onApiBizFailed: function(handler) {
        EzWebAppClient.apiBizFailedHandler = handler;
    },
    onApiCallError: function(handler) {
        EzWebAppClient.apiCallErrorHandler = handler;
    },
    callApiService: function(url, data) {
        let sessionId = EzWebAppClient.getSessionId();
        if (sessionId.length == 32) {
            data.sessionId = sessionId;
            EzWebAppClient.callApi(url, data);
        } else {
            EzWebAppViewHelper.alertSessionExpired();
        }
    },
    handleApiSuccessResult: function(url, data) {
        if (url === EzApiUrl.GET_APP_DATA) {
            EzWebAppBizService.handleAppData(data);
        }
        if (EzWebAppClient.apiBizSuccessHandler!==undefined) {
            EzWebAppClient.apiBizSuccessHandler(url, data);
        }
    },
    handleApiFailedResult: function(url, message) {
        if (EzWebAppClient.apiBizFailedHandler!==undefined) {
            EzWebAppClient.apiBizFailedHandler(url, message);
        }
    },
    callApi: function(url, data) {
        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            success: function(response) {
                EzWebAppClient.apiCallSuccessHandler(url, response);
            },
            error: function(xhr) {
                if (EzWebAppClient.apiCallErrorHandler!==undefined) {
                    EzWebAppClient.apiCallErrorHandler(xhr);
                }
            }
        });
    },
    apiCallSuccessHandler: function(url, respData) {
        if (respData.sessionExpired) {
            EzWebAppClient.alertSessionExpired();
        } else {
            if (respData.success) {
                EzWebAppClient.handleApiSuccessResult(url, respData);
            } else {
                EzWebAppClient.handleApiFailedResult(url, respData.message);
            }
        }
    },
    alertSessionExpired: function() {
        EzWebAppClient.removeSessionCookie();
        EzWebAppViewHelper.showModalAlert(
            'Application Error',
            'Login session has been expired, please re-login',
            function() {
                window.location.replace('login.htm');
            }
        );
    },
    getSessionId: function() {
        let name = EzWebAppClient.sessionIdCookieName + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for(let i = 0; i <ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    },
    removeSessionCookie: function() {
        document.cookie = EzWebAppClient.sessionIdCookieName +'=; Path=/webapp; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    },
    renderPager: function(pageResult) {
        let prevDisabled = pageResult.hasPrevious ? '' : 'disabled';
        let prevClass = pageResult.hasPrevious ? 'button button-rounded waves-effect waves-float' : 'button-grey';
        let prevOnClick = pageResult.hasPrevious ? 'onClick="EzWebAppClient.prev()"' : '';

        let nextDisabled = pageResult.hasNext ? '' : 'disabled';
        let nextClass = pageResult.hasNext ? 'button button-rounded waves-effect waves-float' : 'button-grey';
        let nextOnClick = pageResult.hasNext ? 'onClick="EzWebAppClient.next()"' : '';

        let pagerHTML = ''+
        '<li class="previous '+prevDisabled+'"><a '+prevOnClick+'><div class="padding-pagination '+prevClass+'"><span aria-hidden="true">←</span>&nbsp;&nbsp;Previous</div></a></li>'+
        '<li><a><div class="padding-pagination">Page '+pageResult.pageNumber+' of '+pageResult.totalPage+'</div></a></li>'+
        '<li class="next '+nextDisabled+'"><a '+nextOnClick+'><div class="padding-pagination '+nextClass+'">Next&nbsp;&nbsp;<span aria-hidden="true">→</span></div></a></li>';
        return pagerHTML;
    }
};

var EzWebAppHTMLTemplate = {
    leftMenu: ''+
        '<li class="active">' +
            '<a id="left-menu-url" href="">' +
                '<i id="left-menu-icon" class="material-icons"></i>' +
                '<span id="left-menu-name"></span>' +
            '</a>' +
        '</li>'
};
