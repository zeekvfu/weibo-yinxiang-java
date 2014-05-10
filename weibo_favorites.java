package weibo_yinxiang;


import java.util.Arrays;
import java.sql.Timestamp;

import weibo4j.Favorite;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;


class weibo_favorites {
	private String access_token;
	private Timestamp start_timestamp;

	// 在收藏页面中，每一页收藏的微博数目
	private static final int one_page_count = 20;

	// 存放微博收藏对象 JSONObject 的解析结果
	private class WeiboFavoriteJSON {
		public int total_number;				// 暂时不用
		public int current_page_count;		
		public String[] favorite_weibo_array = null;

		// constructor
		public WeiboFavoriteJSON() {
			favorite_weibo_array = new String[one_page_count];
		}
	}


	// constructor
	public weibo_favorites(String access_token) {
		this.access_token = access_token;
	}	

	// get current timestamp
	private static Timestamp getCurrentTimestamp() {
		java.util.Date date= new java.util.Date();
		return new Timestamp(date.getTime());
	}

	// 解析 JSONObject，将结果保存至 WeiboFavoriteJSON 中
	private boolean parseWeiboFavoriteJSON(JSONObject in,
			WeiboFavoriteJSON out) {
		try {
			out.total_number = in.getInt("total_number");
			JSONArray json_array = in.getJSONArray("favorites");
			// 遍历 JSONArray
			out.current_page_count = json_array.length();
			for (int index = 0; index < out.current_page_count; ++index)
				out.favorite_weibo_array[index] = json_array.getJSONObject(
						index).getString("status");
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 调试：查看 parseWeiboFavoriteJSON 解析结果
	private void debugInfo(WeiboFavoriteJSON weibo_favorite_json) {
		Log.logInfo("WeiboFavoriteJSON\t"
				+ Integer.toString(weibo_favorite_json.current_page_count)
				+ "\t"
				+ Arrays.toString(weibo_favorite_json.favorite_weibo_array));
		return;
	}

	// 处理收藏列表
	public void handleFavorites() {
		start_timestamp = getCurrentTimestamp();
		Favorite fm = new Favorite();
		fm.client.setToken(access_token);
		WeiboFavoriteJSON weibo_favorite_json = new WeiboFavoriteJSON();
		do {
			try {
				JSONObject ids = fm.getFavoritesIds();
				// Log.logInfo(ids.toString());
				if (false == parseWeiboFavoriteJSON(ids, weibo_favorite_json)) {
					break;
				}
				debugInfo(weibo_favorite_json);
				weibo_favorite_to_yinxiang.handleWeiboFavoriteArray(access_token,
						weibo_favorite_json.favorite_weibo_array,
						weibo_favorite_json.current_page_count);
			} catch (WeiboException e) {
				e.printStackTrace();
			}
		} while (false);
		// while (weibo_favorite_json.current_page_count > 0);
		return;
	}

	public Timestamp getStartTimestamp() {
		return start_timestamp;
	} 
}


