package weibo_yinxiang;


import java.sql.Timestamp;


// 说明：
// @我的印象笔记，评论后保存微博。如果保存失败，@我的印象笔记 账户将会给用户发送微博提醒，其中包含了原微博的 URL 或 id
// 新浪微博 API 禁止了第三方软件的私信访问权限！！！sigh.
// 如果新浪微博 API 允许第三方软件访问私信，程序可以通过私信，对之前保存失败的微博重新保存。
// 目前，只能手工处理保存失败的微博


class yinxiang_failed_clip {
	private String access_token;
	private Timestamp start_timestamp;
	private String[] failed_clip_array;

	// constructor 需要 access_token 和微博 id 作为参数
	public yinxiang_failed_clip(String access_token,
			Timestamp start_timestamp) {
		// TODO Auto-generated constructor stub
		this.access_token = access_token;
		this.start_timestamp = start_timestamp;
	}

	public void handleFailedClip() {

	}
}


