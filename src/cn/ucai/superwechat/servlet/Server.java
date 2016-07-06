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
		// 1、获取服务端状态
		/*case I.REQUEST_SERVERSTATUS:
			getServerStatus(response);
			break;*/
		// 3、取消注册
		/*case I.REQUEST_UNREGISTER:
			unRegister(request,response);
			break;*/
		// 4、用户登录
		/*case I.REQUEST_LOGIN:
			login(response, request);
			break;*/
		// 5、下载用户或群组的头像
		/*case I.REQUEST_DOWNLOAD_AVATAR:
			downloadAvatar(request,response);
			break;*/
		// 7、更新用户昵称
		/*case I.REQUEST_UPDATE_USER_NICK:
			updateNick(request,response);
			break;*/
		// 8、更新用户密码
		/*case I.REQUEST_UPDATE_USER_PASSWORD:
			updatePassword(request,response);
			break;*/
		// 9、下载好友列表，显示全部数据
		/*case I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST:
			downloadContactAllList(request, response);
			break;*/
		// 10、下载好友列表，分页显示的数据
		/*case I.REQUEST_DOWNLOAD_CONTACT_PAGE_LIST:
			downloadContactPageList(request, response);
			break;*/
		/*case I.REQUEST_ADD_CONTACT:
			// 11、添加好友
			addContact(request, response);
			break;*/
		/*case I.REQUEST_DELETE_CONTACT:
			// 12、删除好友
			deleteContact(request, response);
			break;*/
		// 13、根据用户名查找用户
		/*case I.REQUEST_FIND_USER:
			findUserByUserName(request, response);
			break;*/
		// 14、根据用户名或昵称，模糊分页查询用户列表
		/*case I.REQUEST_FIND_USERS_FOR_SEARCH:
			findUsersForSearch(request, response);
			break;*/
		case I.REQUEST_UPDATE_GROUP_NAME:
			// 16、更新群组名称
			updateGroupName(request,response);
			break;
		case I.REQUEST_ADD_GROUP_MEMBER:
			// 17、添加群组成员
			addGroupMember(request,response);
			break;
		case I.REQUEST_ADD_GROUP_MEMBERS:
			// 18、添加多个群组成员
			addGroupMembers(request,response);
			break;
		case I.REQUEST_DOWNLOAD_GROUP_MEMBERS:
			// 19、根据群组id，下载全部群组成员
			downloadGroupMembersByGroupId(request,response);
			break;
		case I.REQUEST_DOWNLOAD_GROUP_MEMBERS_BY_LIMIT:
			// 20、根据群组id，分页下载群组成员
			downloadGroupMembersPagesByGroupId(request,response);
			break;
		case I.REQUEST_DOWNLOAD_GROUP_MEMBERS_BY_HXID:
			// 21、根据环信id，下载全部群组成员
			downloadGroupMembersByHxId(request,response);
			break;
		case I.REQUEST_DOWNLOAD_GROUP_MEMBERS_BY_HXID_LIMIT:
			// 22、根据环信id，分页下载群组成员
			downloadGroupMembersPagesByHxId(request,response);
			break;
		case I.REQUEST_DELETE_GROUP_MEMBER:
			// 23、删除单个群组成员
			deleteGroupMember(request,response);
			break;
		case I.REQUEST_DELETE_GROUP_MEMBERS:
			// 24、删除多个群组成员
			deleteGroupMembers(request,response);
			break;
		case I.REQUEST_DELETE_GROUP:
			// 25、删除群组
			deleteGroup(request,response);
			break;
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
	
	private void updateGroupName(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter(I.Group.GROUP_ID);
		String groupNewName = request.getParameter(I.Group.NAME);
		Result result = biz.updateGroupNameByGroupId(groupId,groupNewName);
		JsonUtil.writeJsonToClient(result, response);
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
	
	/**
	 * 添加多成员信息
	 * @param request
	 * @param response
	 */
	private void addGroupMembers(HttpServletRequest request, HttpServletResponse response) {
		String userNameArr = request.getParameter(I.Member.USER_NAME);
		String hxId = request.getParameter(I.Member.GROUP_HX_ID);
		Result result = biz.addGroupMembers(userNameArr,hxId);
		JsonUtil.writeJsonToClient(result, response);
	}

	/**
	 * 添加成员信息
	 * @param request
	 * @param response
	 */
	private void addGroupMember(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter(I.Member.USER_NAME);
		String hxId = request.getParameter(I.Member.GROUP_HX_ID);
		Result result = biz.addGroupMember(userName,hxId);
		JsonUtil.writeJsonToClient(result, response);
	}
	
	private void deleteGroup(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter(I.Group.GROUP_ID);
		Result result = biz.deleteGroup(groupId);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void deleteGroupMembers(HttpServletRequest request, HttpServletResponse response) {
		String userNames = request.getParameter(I.Member.USER_NAME);
		String groupId = request.getParameter(I.Member.GROUP_ID);
		Result result = biz.deleteGroupMembers(userNames, groupId);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void deleteGroupMember(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter(I.Member.GROUP_ID);
		String userName = request.getParameter(I.Member.USER_NAME);
		Result result = biz.deleteGroupMember(userName,groupId);
		JsonUtil.writeJsonToClient(result, response);
	}

	/**
	 * 根据环信id，下载全部群组成员
	 * @param request
	 * @param response
	 */
	private void downloadGroupMembersByHxId(HttpServletRequest request, HttpServletResponse response) {
		String hxId = request.getParameter(I.Member.GROUP_HX_ID);
		Result result = biz.downloadGroupMembersByHxId(hxId);
		JsonUtil.writeJsonToClient(result, response);
	}
	
	/**
	 * 根据环信id，分页下载群组成员
	 * @param request
	 * @param response
	 */
	private void downloadGroupMembersPagesByHxId(HttpServletRequest request, HttpServletResponse response) {
		String hxId = request.getParameter(I.Member.GROUP_HX_ID);
		String pageId = request.getParameter(I.PAGE_ID);
		String pageSize = request.getParameter(I.PAGE_SIZE);
		Result result = biz.downloadGroupMembersPagesByHxId(hxId,pageId,pageSize);
		JsonUtil.writeJsonToClient(result, response);
	}

	private void downloadGroupMembersByGroupId(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter(I.Member.GROUP_ID);
		// 下载全部群组成员
		Result result = biz.downloadGroupMembersByGroupId(groupId);
		JsonUtil.writeJsonToClient(result, response);
	}
	
	
	private void downloadGroupMembersPagesByGroupId(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter(I.Member.GROUP_ID);
		String pageId = request.getParameter(I.PAGE_ID);
		String pageSize = request.getParameter(I.PAGE_SIZE);
		// 分页下载群组成员
		Result result = biz.downloadGroupMembersPagesByGroupId(groupId,pageId,pageSize);
		JsonUtil.writeJsonToClient(result, response);		
	}
}
