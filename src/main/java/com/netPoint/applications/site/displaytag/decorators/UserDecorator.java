/**
 * 
 */
package com.netPoint.applications.site.displaytag.decorators;

import org.displaytag.decorator.TableDecorator;

import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public class UserDecorator extends TableDecorator {
	public String getMail()
    {
        User user= (User) getCurrentRowObject();
        int lId= user.getId();
        
        return "<a href=\"detailUser/" + lId +"\" >"+ user.getLogin()+"</a>";
    }

}
