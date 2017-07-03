var staticURL = $('#staticURL').val();

$(document).ready( function() {
	$('.top-navi').on('click', function() { menu.topMenu($(this)); });
	$('.left-navi-sub').on('click', function() { menu.leftMenu($(this)); }); 
	
});

var menu = {
	_dashboardURL : staticURL+'/main/dashboard.do',
	_serviceURL : staticURL+'/service/inServiceList.do',
	_statURL : staticURL+'/stat/serviceMStatList.do',
	_logURL : staticURL+'/log/logTTSList.do',
	_systemURL : staticURL+'/system/actTypeList.do',
	_siteURL : staticURL+'/site/accountList.do',
	_monitoringURL : staticURL+'/monitoring/monitoringUserList.do',

	setMenu: function() {
		var tmenu = common.getQueryString(["tmenu"]);
		var lmenu = common.getQueryString(["lmenu"]);
	    
		if(tmenu == '') {
			this._dashboardURL  += common.setQueryString(["page"], {"tmenu": "navi-dashboard"});
			common.redirect(this._dashboardURL);
			
		}else {
			var leftmenuId = '#left-'+tmenu;
			
			$('#'+tmenu).addClass("on");
			$(leftmenuId).show();
			
			if(lmenu == '') {
				$(leftmenuId).find('.left-navi-sub:eq(0)').addClass("on");
			}else {
				lmenu = Number(lmenu) -1;
				$(leftmenuId).find('.left-navi-sub:eq('+lmenu+')').addClass("on");
			}
		}
	},
	topMenu: function($this) {
		var id = $this.attr('id');
		var url;
		
		switch (id) {
		case 'navi-dashboard':
			url = this._dashboardURL + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		case 'navi-service':
			url = this._serviceURL + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		case 'navi-monitoring':
			url = this._monitoringURL + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		case 'navi-stat':
			url = this._statURL  + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		case 'navi-log':
			url = this._logURL  + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		case 'navi-system':
			url = this._systemURL  + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		case 'navi-site':
			url = this._siteURL  + common.setQueryString(["page"], {"tmenu": id});
			common.redirect(url);
			break;
			
		default:
			
			break;
		}
	},
	leftMenu: function($this) {
		var leftMenuUrl = $this.attr('data-url');

		var leftMenuArr = leftMenuUrl.split('?');
		var leftUrl = leftMenuArr[0];
		var leftParam = leftMenuArr[1];
		
		leftUrl += common.setQueryString([leftMenuUrl,"tmenu"]);
		leftUrl += "&"+leftParam;
		
		common.redirect(leftUrl);
	}
};