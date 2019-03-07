var WebIM = {};
WebIM.config = {
	xmppURL : 'im-api.easemob.com', // xmpp Server

	apiURL : 'http://a1.easemob.com', // rest Server

	appkey : 'lifeshs#testapp', // App key
	//appkey : 'lifeshs#lifekeepers',

	https : false, // 是否使用https

	isMultiLoginSessions : true, // 是否开启多页面同步收消息

	isAutoLogin : true,

	password : "123456",

	fileUrl: 'http://a1.easemob.com/' + 'lifeshs/' + 'testapp/' + 'chatfiles/',

    autoReconnectNumMax: 10,

    autoReconnectInterval: 10

};
