package com.creativesci.liferay.documentlibrary;

import com.creativesci.liferay.documentlibrary.tracking.GoogleAnalytics;
import com.creativesci.liferay.documentlibrary.tracking.TrackingFilter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class LiferayUtil {

	private static LiferayUtil INSTANCE;

	LiferayUtil(){}

	public static LiferayUtil getInstance() {
		if ( INSTANCE == null )
			setInstance(new LiferayUtil());
		return INSTANCE;
	}

	protected static void setInstance(LiferayUtil instance) {
		INSTANCE = instance;
	}

	public boolean isTrackingEnabled() {
		return GetterUtil.getBoolean(PropsUtil.get("document.download.tracking.enabled"), Boolean.TRUE);
	}

	public String getGoogleAnalyticsAccount() {
		return GetterUtil.getString(PropsUtil.get("document.download.tracking.account"), "UA-101011-9");
	}

	private long getLiferayServiceAccountId() {
		return Long.valueOf(GetterUtil.getString(PropsUtil.get("document.download.tracking.userid"), "87000"));
	}

	public PDFFileEntry createFileEntry(final String[] pathArray) throws Exception {
		User user = UserLocalServiceUtil.getUser(getLiferayServiceAccountId());
		PrincipalThreadLocal.setName(user.getUserId());
		PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(user);
		PermissionThreadLocal.setPermissionChecker(permissionChecker);
		if (pathArray.length == 1) {
			long dlFileShortcutId = GetterUtil.getLong(pathArray[0]);
			DLFileShortcut dlFileShortcut = DLAppServiceUtil.getFileShortcut(dlFileShortcutId);
			return new PDFFileEntry(DLAppServiceUtil.getFileEntry(dlFileShortcut.getToFileEntryId()));
		}
		else if (pathArray.length == 2) {
			long groupId = GetterUtil.getLong(pathArray[0]);
			return new PDFFileEntry(DLAppServiceUtil.getFileEntryByUuidAndGroupId(pathArray[1], groupId));
		}
		else if (pathArray.length == 3) {
			long groupId = GetterUtil.getLong(pathArray[0]);
			long folderId = GetterUtil.getLong(pathArray[1]);
			String fileName = HttpUtil.decodeURL(pathArray[2]);

			if (fileName.contains(StringPool.QUESTION)) {
				fileName = fileName.substring(0, fileName.indexOf(StringPool.QUESTION));
			}
			return new PDFFileEntry(DLAppServiceUtil.getFileEntry(groupId, folderId, fileName));
		}
		else {
			long groupId = GetterUtil.getLong(pathArray[0]);
			String uuid = pathArray[3];
			return new PDFFileEntry(DLAppServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId));
		}
	}


	public HttpServletRequest getOriginalRequest(final ServletRequest servletRequest) {
		return PortalUtil.getOriginalServletRequest((HttpServletRequest) servletRequest);
	}

	public Log getLogger() {
		return LogFactoryUtil.getLog(TrackingFilter.class);
	}

	public UUID getUUID() {
		return UUID.randomUUID();
	}
}
