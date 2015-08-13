package weibo_yinxiang;


import weibo4j.Comments;
import weibo4j.Favorite;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.Favorites;
import weibo4j.model.WeiboException;


// 利用新浪微博的「@我的印象笔记」功能，将收藏的微博保存到印象笔记账户中
class WeiboFavoriteToYinxiang {
	private String access_token;
	private String favorite_weibo_id;

	private static final String yinxiang_comment = "@我的印象笔记";
	private String comment_id;
	private boolean exit_flag = false;			// 是否出现了严重的错误，以致于应该退出当前逻辑


	// constructor 需要 access_token 和微博 id 作为参数
	public WeiboFavoriteToYinxiang(String access_token, String favorite_weibo_id) {
		// TODO Auto-generated constructor stub
		this.access_token = access_token;
		this.favorite_weibo_id = favorite_weibo_id;
	}

	public boolean getExitFlag() {
		return exit_flag;
	}

	// 评论微博，内容为：@我的印象笔记
	private boolean commentWeibo() {
		Comments cm = new Comments();
		cm.client.setToken(access_token);
		try {
			Comment comment = cm.createComment(yinxiang_comment, favorite_weibo_id);
			 Log.logInfo(comment.toString());
			comment_id = comment.getIdstr();
		} catch (WeiboException e) {
			e.printStackTrace();
			int error_code = e.getErrorCode();
			// 用户请求特殊接口 (%s) 频次超过上限
			if (10024 == error_code) {
				Log.logInfo("评论请求次数超过上限！");
				// System.exit(error_code);
				exit_flag = true;
			}
			// 被评论的微博不存在，取消收藏
			else if (20101 == error_code) {
				Log.logInfo(favorite_weibo_id + "\tTarget weibo does not exist, now will delete it ...");
				deleteFavorite();
			}
			return false;
		}
		return true;
	}

	// 删除评论，以减少对被评论者的干扰
	private void deleteComment() {
		Comments cm = new Comments();
		cm.client.setToken(access_token);
		try {
			Comment com = cm.destroyComment(comment_id);
			Log.logInfo(com.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return;
	}

	// 删除收藏
	private void deleteFavorite() {
		Favorite fm = new Favorite();
		fm.client.setToken(access_token);
		try {
			Favorites favors = fm.destroyFavorites(favorite_weibo_id);
			// Log.logInfo(favors.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return;
	}

	// 处理收藏。delete_favorite 表示是否需要移除收藏
	// 因为如果上次 @我的印象笔记 后，保存微博失败，该微博其实已经被移除收藏了。
	public void handleFavorite(boolean delete_favorite) {
		if (true == commentWeibo()) {
			// 评论之后，不要立即删除微博，等印象笔记处理完，再删除
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			deleteComment();
			if (true == delete_favorite) {
				deleteFavorite();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		return;
	}

	// 处理数组 favorite_weibo_array[] 中收藏的所有微博
	// 注意事项：数组 favorite_weibo_array[] 中并不一定所有的元素都用上了，即 array_length <= favorite_weibo_array.length
	public static void handleWeiboFavoriteArray(boolean delete_favorite, String access_token, String[] favorite_weibo_array, int array_length) {
		for (int index = 0; index < array_length; ++index) {
			WeiboFavoriteToYinxiang object = new WeiboFavoriteToYinxiang(access_token, favorite_weibo_array[index]);
			object.handleFavorite(delete_favorite);
			if (true == object.getExitFlag()) {
				break;
			}
		}
		return;
	}

	// 处理数组 weibo_ids 中的微博
	public static void handleWeiboFavoriteIds(boolean delete_favorite, String access_token, String... weibo_ids) {
		for ( String weibo_id : weibo_ids) {
			WeiboFavoriteToYinxiang object = new WeiboFavoriteToYinxiang(access_token, weibo_id);
			object.handleFavorite(delete_favorite);
			if (true == object.getExitFlag()) {
				break;
			}
		}
		return;
	}
}


