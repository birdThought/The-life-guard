var WebIM = {};
var appName = "lifekeepers";
//var appName = "lifekeepers-test";

WebIM.config = {
	xmppURL : 'im-api.easemob.com', // xmpp Server

	apiURL : 'http://a1.easemob.com', // rest Server

	appkey : 'lifeshs#' + appName, // App key

	https : false, // 是否使用https

	isMultiLoginSessions : true, // 是否开启多页面同步收消息

	isAutoLogin : true,

	password : "123456",

	fileUrl: 'http://a1.easemob.com/' + 'lifeshs/' + appName + '/' + 'chatfiles/',

    autoReconnectNumMax: 10,

    autoReconnectInterval: 10

};
