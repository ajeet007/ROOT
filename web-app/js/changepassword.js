$(document).ready(function() {

      var submitForm = $('#changePassword');
       submit = false;

    $('#changePassword').click(function(){


     if($('#password').val()=='' ||$('#password2').val()==''|| $('#oldpassword').val()=='') {
       $("#validate").html('<font color=white>'+messages.js.password.notBlank.message()+'</font>')
           return false;
        }

        if($('#password').val()!=$('#password2').val()) {
         $("#validate").html('<font color=white>'+messages.js.password.notIdentical.message()+'</font>')
            return false;
        }

     r=confirm(messages.js.password.change.message());

      if(r){
         return true;

      }
      else{
        return false;
      }


    })

})
