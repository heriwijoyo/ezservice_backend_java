var EzWebAppViewHelper = {
    start: function() {
        EzWebAppViewHelper.renderViewComponents();
        $('#common-modal-close').click(function(){
            $('#common-modal').removeClass('show');
            $('#common-modal').addClass('fade');
            EzWebAppViewHelper.onModalClose();
        });
        $('.btn-logout').click(function(){
            EzWebAppClient.logout();
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
    renderViewComponents: function() {
        let commonModal = ''+
            '<div class="modal fade" id="common-modal" tabindex="-1" role="dialog">'+
                '<div class="modal-dialog modal-lg" role="document">'+
                    '<div class="modal-content">'+
                        '<div class="modal-header">'+
                            '<h4 class="modal-title" id="common-modal-title"></h4>'+
                        '</div>'+
                        '<div class="modal-body">'+
                            '<h3 id="common-modal-message"></h3>'+
                        '</div>'+
                        '<div class="modal-footer">'+
                            '<button id="common-modal-close" type="button" class="btn btn-link waves-effect" data-dismiss="modal">CLOSE</button>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</div>';
        $(document.body).append($.parseHTML(commonModal));
    },
    showAppModal: function(modalId) {
        $('#'+ modalId).removeClass('fade');
        $('#'+ modalId).addClass('show');
    },
    hideAppModal: function(modalId) {
        $('#'+ modalId).removeClass('show');
        $('#'+ modalId).addClass('fade');
    }
};
var EzApiUrl = {
    GET_APP_DATA: 'api/getAppData.json',
    GET_DASHBOARD_DATA: 'api/getDashboardData.json',
    COMMON_SWITCH_FLAG: 'api/adminCommonSwitchFlag.json'
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
        for (let i = 0; i < response.data.specialMenu.length; i++) {
            let menu = $.parseHTML(EzWebAppHTMLTemplate.leftMenu);
            $(menu).find('#left-menu-url').attr('href', response.data.specialMenu[i].menuUrl);
            $(menu).find('#left-menu-name').html(response.data.specialMenu[i].menuName);
            $(menu).find('#left-menu-icon').html(response.data.specialMenu[i].menuIcon);
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
            EzWebAppClient.alertSessionExpired();
        }
    },
    postMultipartFormData: function(formId, postData, url, tag) {
        let sessionId = EzWebAppClient.getSessionId();
        if (sessionId.length != 32) {
            EzWebAppClient.alertSessionExpired();
        } else {
            if (postData == undefined || postData == null) {
                postData = {};
            }
            postData.sessionId = sessionId;

            let data = new FormData($('#'+formId)[0]);

            for (const [key, value] of Object.entries(postData)) {
                data.append(key, value);
            }

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: url,
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function(response) {
                    EzWebAppClient.apiCallSuccessHandler(url+tag, response);
                },
                error: function(xhr) {
                    if (EzWebAppClient.apiCallErrorHandler!==undefined) {
                        EzWebAppClient.apiCallErrorHandler(xhr);
                    }
                }
            });
        }
    },
    postMultipartForm: function(formId, postData, url, tag) {
        let sessionId = EzWebAppClient.getSessionId();
        if (sessionId.length != 32) {
            EzWebAppClient.alertSessionExpired();
        } else {
            if (postData == undefined || postData == null) {
                postData = {};
            }
            postData.sessionId = sessionId;

            let data = new FormData($('#'+formId)[0]);
            data.append('postData', JSON.stringify(postData));

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: url,
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function(response) {
                    EzWebAppClient.apiCallSuccessHandler(url+tag, response);
                },
                error: function(xhr) {
                    if (EzWebAppClient.apiCallErrorHandler!==undefined) {
                        EzWebAppClient.apiCallErrorHandler(xhr);
                    }
                }
            });
        }
    },
    commonSwitchFlag: function(data) {
        EzWebAppClient.callApiService(EzApiUrl.COMMON_SWITCH_FLAG, data);
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
    },
    getUrlParameter: function(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
        return false;
    },
    logout: function() {
        if (confirm('Are you sure want to logout?')) {
            EzWebAppClient.removeSessionCookie();
            window.location.replace('login.htm');
        }
    },
    isBlank: function(param) {
        if (param === undefined || param == null || param == '') {
            return true;
        }
        return false;
    },
    isNotBlank: function(param) {
        return !EzWebAppClient.isBlank(param);
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
