package weibo_yinxiang;


class WeiboYinxiang {

	public WeiboYinxiang() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 WeiboFavorites object = new WeiboFavorites(args[0]);
		 object.handleFavorites(true);

		// // 手工处理保存失败的微博
		// WeiboFavoriteToYinxiang.handleWeiboFavoriteIds(false, args[0], "3708635370948492", "3708635857456127", "3708635887128136", "3708682946972397");

		// // 手工处理保存失败的微博
		// String[] failed_clip_array = { "3708635370948492", "3708635857456127", "3708635887128136", "3708682946972397" };
		// WeiboFavoriteToYinxiang.handleWeiboFavoriteArray(false, args[0], failed_clip_array, failed_clip_array.length);

		// // 未实现
		// yinxiang_failed_clip obj2 = new YinxiangFailedClip(args[0], obj1.getStartTimestamp());
		// obj2.handleFailedClip();

		return;
	}
}


