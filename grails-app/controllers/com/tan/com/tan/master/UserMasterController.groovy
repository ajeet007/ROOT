/*
Revision History:
Developer Name  : Ajeet Singh
Date            : 27-June-2014
Reason          : Modified to find all roles assigned to a user.
*/

package com.tan.com.tan.master
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import com.tan.User
import com.tan.Role
import javax.jws.soap.SOAPBinding.Use
import com.tan.UserRole


@Secured(['IS_AUTHENTICATED_FULLY'])
class UserMasterController {
    def passwordEncryptionService
    def passwd
    def depasswd
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "showUserinformation",params: params)
    }

    //This function is used for show selected usr information

    def  showUserinformation(){
        def userInfo= [:]
        try{

            if (params.userId) {
                def user= User.findById(params.userId)
                userInfo.userInformation=user
                userInfo.role=UserRole.findAllByUser(user).role

                render userInfo as JSON
            }

        }catch(Exception e)
        {
            println("There are some problem in show information of user"+e)
        }
    }


    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tbl_userInstanceList: User.list(params), tbl_userInstanceTotal: User.count()]
    }

    def create = {

        def tbl_userInstance = new User()
        tbl_userInstance.properties = params

        return [tbl_userInstance: tbl_userInstance]

    }

    def delete = {
        println("delete action called"+params.userID);
        def tbl_update=User.findById(params.userId);
        def tbl_userInstance = User.get(tbl_update.id)
        if (tbl_userInstance) {
            try {
                tbl_userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), tbl_update.username])}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'User.label', default: 'User'), tbl_update.username])}"
                redirect(action: "create", id: tbl_update.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'User.label', default: 'User'), tbl_update.username])}"
            redirect(action: "create")
        }

    }


    //This is use

    def showLogUser={

        def logInstance1=User.findAllByUsername(params.userName,[sort:"username"]);
        render logInstance1 as JSON;
    }
    def delete1={
        def logInstance2
        if (params.agentID) {
            logInstance2 = User.findAllByUsername(params.userID);
        }
        else {
            logInstance2 = User.findAllByUsername(session.user);
        }
        render logInstance2 as JSON;
    }



    def userExistsOrNot ={

        def user=User.findAllByUsername(params.loginName);
        if(user)
            render  'true'
        else
            render 'false'

        return ;
    }




}


