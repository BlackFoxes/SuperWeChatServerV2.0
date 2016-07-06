package cn.ucai.superwechat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.biz.ISuperWeChatBiz;
import cn.ucai.superwechat.biz.SuperWeChatBizImpl;
import cn.ucai.superwechat.pojo.Group;
import cn.ucai.superwechat.pojo.Location;
import cn.ucai.superwechat.pojo.User;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.JsonUtil;
import cn.ucai.superwechat.utils.Utils;

@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISuperWeChatBiz biz;
    public Server() {
        super();
        biz = new SuperWeChatBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的参数
		String strReq = request.getParameter("request");
		System.out.println("strReq:"+strReq);
		switch (strReq) {
		case I.REQUEST_FIND_GROUP_BY_USER_NAME:
			// 26、根据用户名，下载指定下载指定用户的群组列表
			findAllGroupByUserName(request,response);
			break;
		case I.REQUEST_FIND_PUBLIC_GROUPS:
			// 27、分页下载全部的公开群
			findPublicGroups(request,response);
			break;
		case I.REQUEST_FIND_GROUP_BY_GROUP_NAME:
			// 28、根据群组名称，模糊查询全部群组列表
			findGroupByGroupName(request,response);
			break;
		case I.REQUEST_FIND_GROUP_BY_ID:
			// 29、根据群组ID查询群组信息
			findGroupByGroupId(request,response);
			break;
		case I.REQUEST_FIND_GROUP_BY_HXID:
			// 30、根据环信ID查询群组信息
			findGroupByHxId(request,response);
			break;
		case I.REQUEST_UPLOAD_LOCATION:
			// 31、上传用户地理位置
			uploadLocation(request, response);
			break;
		case I.REQUEST_UPDATE_LOCATION:
			// 32、更新用户地理位置
			updateLocation(request, response);
			break;
		case I.REQUEST_DOWNLOAD_LOCATION:
			// 33、分页下载附近的人
			downloadLocation(request, response);
			break;
		case I.REQUEST_FIND_PUBLIC_GROUP_BY_HXID:
			// 34、根据环信ID查询公开群组信息
			findPublicGroupByHxId(request,response);
			break;
		}
	}
	
	
	private void downloadLocation(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter(I.Location.USER_NAME);
		String pageId = request.getParameter(I.PAGE_ID);
		String pageSize = request.getParameter(I.PAGE_SIZE);
		Result result = biz.downloadLocation(userName,pageId,pageSize);
		JsonUtil.writeJsonToClient(result, response);
	}

	/**
	 * 更新用户地理位置
	 * @param request
	 * @param response
	 */
	private void updateLocation(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter(I.Location.USER_NAME);
		String latitude = request.getParameter(I.Location.LATITUDE);
		String longitude = request.getParameter(I.Location.LONGITUDE);
		String isSearched = request.getParameter(I.Location.IS_SEARCHED);
		Location location = new Location(userName, 
				Double.parseDouble(latitude), Double.parseDouble(longitude), 
				Boolean.parseBoolean(isSearched),System.currentTimeMillis()+"");
		Result result = biz.updateUserLocation(location);
		JsonUtil.writeJsonToClient(result, response);
	}

	/**
	 * 上传用户地理位置
	 * @param request
	 * @param response
	 */
	private void uploadLocation(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter(I.Location.USER_NAME);
		String latitude = request.getParameter(I.Location.LATITUDE);
		String longitude = request.getParameter(I.Location.LONGITUDE);
		Location location = new Location(userName, 
				Double.parseDouble(latitude), Double.parseDouble(longitude), 
				Utils.int2boolean(I.LOCATION_IS_SEARCH_ALLOW),System.currentTimeMillis()+"");
		Result result = biz.uploadUserLocation(location);
		JsonUtil.writeJsonToClient(result, response);
	}

	/**
	 * 根据群组名称，模糊查询群组信息
	 * @param request
	 * @param response
	 */
	private void findGroupByGroupName(HttpServletRequest request, HttpServletResponse response) {
		String groupName = request.getParameter(I.Group.NAME);
		Result result = biz.findGroupByGroupName(groupName);
		JsonUtil.writeJsonToClient(result, response);
	}

	/**
	 * 查找所有的公开群
	 * @param request
	 * @param response
	 */
	private void findPublicGroups(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter(I.User.USER_NAME);
		int pageId = Integer.parseInt(request.getParameter(I.PAGE_ID));
		int pageSize = Integer.parseInt(request.getParameter(I.PAGE_SIZE));
		System.out.println("findPublicGroups pageid="+pageId+",pageSize="+pageSize);
		Result result = biz.findPublicGroups(userName, pageId, pageSize);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void findAllGroupByUserName(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter(I.User.USER_NAME);
		Result result = biz.findAllGroupByUserName(userName);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void findGroupByHxId(HttpServletRequest request, HttpServletResponse response) {
		String hxId = request.getParameter(I.Group.HX_ID);
		Result result = biz.findGroupByHxId(hxId);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void findPublicGroupByHxId(HttpServletRequest request, HttpServletResponse response) {
		String hxId = request.getParameter(I.Group.HX_ID);
		Result result = biz.findPublicGroupByHxId(hxId);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void findGroupByGroupId(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter(I.Group.GROUP_ID);
		Result result = biz.findGroupByGroupId(groupId);
		JsonUtil.writeJsonToClient(result, response);
	}
}
