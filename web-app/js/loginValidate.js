 /*
Revision History:
Developer Name  : Ajeet Singh
Date            : 24-June-2014
Reason          : login validation
*/

var versionWindow;

$(document).ready(function() {

      $("#submit1").click(function()  {

         var loginStatus= validateLogin();
         if(loginStatus)
           {

          if($('#username').val()!='')
          {
          jQuery.ajax(
           {
               type:'POST',
               url:g.createLink({controller: 'loginValidate', action: 'userLogedinOrNot'}),
               data: "loginname=" + $('#username').val(),
                success:function(data, textStatus) {
                 loginStatus=data;
               if(loginStatus=="true"){

              var r=confirm("User is already logged in. Do you want to kill your previous login?");
                     if (r) {
                         document.forms["loginForm"].submit();
                       // return true;
                     }
                    else {
                         return false;
                     }

                 }
                    else{
                     document.forms["loginForm"].submit();
                   }
               }
              ,error:function(XMLHttpRequest, textStatus, errorThrown) {

               }

           });
          }

         }  else{
             $('#flashMessage').html('');
             return false;
         }

       });

          $('#password').keypress(function(e){

              if(e.keyCode==13){
                  $("#submit1").click();
              }

          });


    function validateLogin(){

        if ($('#username').val() == '') {
            $('#loginName_status').html('<font color=white>Please enter Login Name</font>');
            return false;
        }
        else {
            $('#loginName_status').html('');

        }

        if ($('#password').val() == '') {
            $('#password_status').html('<font color=white>Please enter password</font>');
            return false;
        }
        else {
            $('#password_status').html('');
            return true;
        }


    }



});

