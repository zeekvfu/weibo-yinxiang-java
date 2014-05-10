package weibo_yinxiang;


class weibo_yinxiang {

	public weibo_yinxiang() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weibo_favorites obj1 = new weibo_favorites(args[0]);
		obj1.handleFavorites();

		//		yinxiang_failed_clip obj2 = new yinxiang_failed_clip(args[0], obj1.getStartTimestamp());
		//		obj2.handleFailedClip();

		// 手工处理保存失败的微博
		String[] failed_clip_array = { "3708635370948492", "3708635857456127",
			"3708635887128136", "3708682946972397" };
		weibo_favorite_to_yinxiang.handleWeiboFavoriteArray(args[0],
				failed_clip_array, failed_clip_array.length);
		return;
	}
}


