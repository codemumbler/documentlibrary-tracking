documentlibrary-tracking
========================

This is a Liferay 6.2 hook which enables the Document Library document to be tracked using Google Analytics.

Usage:

  Deploy to Liferay 6.2.x instance.
  
  Create or give permission to a user which has access to all documents you want tracked by Google Analytics.
  
  Add the following properties to the portal-ext.properties file:
  
    document.download.tracking.account=UA-xxxxxx-x
    document.download.tracking.userid=userId
    
  Reboot Liferay.
  
To test settings:
  Open Google Analytics, browse to the Real-Time section.
  Visit document served by your Liferay instance.
  In the Real-Time section you should see the document appear.
