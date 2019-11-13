
var id,cancelDiv_ID ;
 $(document).ready(function()
   {
   $("#receipt1_div").hide();
   $("#ads_div").hide();
   $("#publisher_div").hide();
   $("#calendarForDateRange").hide();
   $("#deferred_div").hide();

   $('#receipt_div').click(function()
      {
          $('#adds_div').css({'font-size':''});
          $('.cash_div').css({'font-size':''});
          $('.cheque_div').css({'font-size':''});
          $('.credit_div').css({'font-size':''});
          $('.debit_div').css({'font-size':''});
          $('.total_div').css({'font-size':''});
          $('#commission_div').css({'font-size':''});
          $('#cancel_div').css({'font-size':''});
          $('#receipt_div').css({'font-size':'20px'});
          $('#publisher_report_div').css({'font-size':''});
          $('#deferred_report_div').css({'font-size':''});
      });
       $('#adds_div').click(function()
      {
          $('#receipt_div').css({'font-size':''});
          $('.cash_div').css({'font-size':''});
          $('.cheque_div').css({'font-size':''});
          $('.credit_div').css({'font-size':''});
          $('.debit_div').css({'font-size':''});
          $('.total_div').css({'font-size':' '});
          $('#commission_div').css({'font-size':''});
          $('#cancel_div').css({'font-size':''});
          $('#adds_div').css({'font-size':'20px'});
          $('#publisher_report_div').css({'font-size':''});
          $('#deferred_report_div').css({'font-size':''});

      });
       $('.cash_div').click(function()
      {
           $('#receipt_div').css({'font-size':''});
           $('#adds_div').css({'font-size':''});
           $('.cheque_div').css({'font-size':''});
           $('.credit_div').css({'font-size':''});
           $('.debit_div').css({'font-size':''});
           $('#cancel_div').css({'font-size':''});
           $('.total_div').css({'font-size':''});
           $('#commission_div').css({'font-size':''});
           $('.cash_div').css({'font-size':'20px'});
           $('#publisher_report_div').css({'font-size':''});
           $('#deferred_report_div').css({'font-size':''});

      });

          $('.cheque_div').click(function()
             {
                 $('#adds_div').css({'font-size':''});
                 $('.cash_div').css({'font-size':''});
                 $('#receipt_div').css({'font-size':''});
                 $('.credit_div').css({'font-size':''});
                 $('.debit_div').css({'font-size':''});
                 $('.total_div').css({'font-size':''});
                 $('#commission_div').css({'font-size':''});
                 $('#cancel_div').css({'font-size':''});
                 $('.cheque_div').css({'font-size':'20px'});
                 $('#publisher_report_div').css({'font-size':''});
                 $('#deferred_report_div').css({'font-size':''});
             });
              $('.credit_div').click(function()
             {
                 $('#receipt_div').css({'font-size':''});
                 $('.cash_div').css({'font-size':''});
                 $('.cheque_div').css({'font-size':''});
                 $('#adds_div').css({'font-size':''});
                 $('.debit_div').css({'font-size':''});
                 $('.total_div').css({'font-size':''});
                 $('#commission_div').css({'font-size':''});
                 $('#cancel_div').css({'font-size':''});
                 $('.credit_div').css({'font-size':'20px'});
                 $('#publisher_report_div').css({'font-size':''});
                 $('#deferred_report_div').css({'font-size':''});

             });
              $('.debit_div').click(function()
             {
                  $('#receipt_div').css({'font-size':''});
                  $('#adds_div').css({'font-size':''});
                  $('.cheque_div').css({'font-size':''});
                  $('.credit_div').css({'font-size':''});
                  $('.cash_div').css({'font-size':''});
                  $('.total_div').css({'font-size':''});
                  $('#commission_div').css({'font-size':''});
                  $('#cancel_div').css({'font-size':''});
                  $('.debit_div').css({'font-size':'20px'});
                  $('#publisher_report_div').css({'font-size':''});
                  $('#deferred_report_div').css({'font-size':''});

             });
            $('.total_div').click(function()
             {
                  $('#receipt_div').css({'font-size':''});
                  $('#adds_div').css({'font-size':''});
                  $('.cheque_div').css({'font-size':''});
                  $('.credit_div').css({'font-size':''});
                  $('.debit_div').css({'font-size':''});
                  $('.cash_div').css({'font-size':''});
                  $('#commission_div').css({'font-size':''});
                  $('#cancel_div').css({'font-size':''});
                  $('.total_div').css({'font-size':'20px'});
                  $('#publisher_report_div').css({'font-size':''});
                  $('#deferred_report_div').css({'font-size':''});

          });

        $('#commission_div').click(function(e)
             {
                  $('#receipt_div').css({'font-size':''});
                  $('#adds_div').css({'font-size':''});
                  $('.cheque_div').css({'font-size':''});
                  $('.credit_div').css({'font-size':''});
                  $('.debit_div').css({'font-size':''});
                  $('.cash_div').css({'font-size':''});
                  $('.total_div').css({'font-size':''});
                  $('#cancel_div').css({'font-size':''});
                  $('#commission_div').css({'font-size':'20px'});
                  $('#publisher_report_div').css({'font-size':''});
                  $('#deferred_report_div').css({'font-size':''});

           });

            $('#cancel_div').click(function()
               {

                    $('#receipt_div').css({'font-size':''});
                    $('#adds_div').css({'font-size':''});
                    $('.cheque_div').css({'font-size':''});
                    $('.credit_div').css({'font-size':''});
                    $('.debit_div').css({'font-size':''});
                    $('.cash_div').css({'font-size':''});
                    $('.total_div').css({'font-size':''});
                    $('#commission_div').css({'font-size':''});
                    $('#cancel_div').css({'font-size':'20px'});
                    $('#publisher_report_div').css({'font-size':''});
                    $('#deferred_report_div').css({'font-size':''});


            });
            $('#publisher_report_div').click(function()
               {

                    $('#receipt_div').css({'font-size':''});
                    $('#adds_div').css({'font-size':''});
                    $('.cheque_div').css({'font-size':''});
                    $('.credit_div').css({'font-size':''});
                    $('.debit_div').css({'font-size':''});
                    $('.cash_div').css({'font-size':''});
                    $('.total_div').css({'font-size':''});
                    $('#commission_div').css({'font-size':''});
                    $('#cancel_div').css({'font-size':''});
                    $('#publisher_report_div').css({'font-size':'20px'});
                    $('#deferred_report_div').css({'font-size':''});

            });

            $('#deferred_report_div').click(function()
               {

                    $('#receipt_div').css({'font-size':''});
                    $('#adds_div').css({'font-size':''});
                    $('.cheque_div').css({'font-size':''});
                    $('.credit_div').css({'font-size':''});
                    $('.debit_div').css({'font-size':''});
                    $('.cash_div').css({'font-size':''});
                    $('.total_div').css({'font-size':''});
                    $('#commission_div').css({'font-size':''});
                    $('#cancel_div').css({'font-size':''});
                    $('#publisher_report_div').css({'font-size':''});
                    $('#deferred_report_div').css({'font-size':'20px'});

            });
  $('#receipt_div').click(function()
  {

  });

  $('#receipt').click(function(){
       $('#bookingID').val('');
            })

   $("#enterBookingID").keypress(function (e) {

      if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
          $("#bookingID_Status").html('<font color=red>'+messages.js.digits.onlyMsg.message()+'</font>').show().fadeOut("slow");
          return false;
     }

    });

  $("#txt_bid").keypress(function (e) {
          if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
              $("#booking_id").html('<font color=red>'+messages.js.digits.onlyMsg.message()+'</font>').show().fadeOut("slow");
              return false;
          }

     });


   $("#ro_id").keypress(function (e) {
          if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
              $("#roid").html('<font color=red>'+messages.js.digits.onlyMsg.message()+'</font>').show().fadeOut("slow");
              return false;
          }


     });



    /* $('#searchAds').click(function(){
     var url =g.createLink({controller: 'accountingReport', action: 'search'});

     window.open (url+'?dateFrom='+$('#dateFrom').val()+'&dateTo='+$('#dateTo').val(),"mywindow","menubar=1,resizable=1,width=730,height=800,scrollbars=1" );

     })
      */

   $('#receipt').click(function(){

        if($('#enterBookingID').val().trim()=="") {
            $("#bookingID_Status").html('<font color=red>'+messages.js.bookId.blankMsg.message()+'</font>').show();
            return false
        }

      else{
           $("#bookingID_Status").html('');
         var url =g.createLink({controller: 'accountingReport', action: 'printReceipt'});
         window.open (url+'?bookingID='+$('#enterBookingID').val(),"mywindow","menubar=1,resizable=1,width=730,height=800,scrollbars=1" );
        }
    });
 $('#searchAds').click(function(){

        var url =g.createLink({controller: 'accountingReport', action: 'adBookingOrCancel'});

        window.open (url+'?dateFrom='+$('#dateFrom').val()+'&dateTo='+$('#dateTo').val()+'&div_id='+cancelDiv_ID,"mywindow1","menubar=1,resizable=1,width=730,height=800,scrollbars=1" );


    });

  $('#showReportCollection').click(function(){
            var url =g.createLink({controller: 'accountingReport', action: 'agentCollection'});
              window.open (url+'?datef='+$('#datef').val()+'&dateto='+$('#dateto').val()+'&paymentTypeID='+id,"mywindow2","menubar=1,resizable=1,width=730,height=800,scrollbars=1" );


    });

    $('#showCommission').click(function(){
            var url =g.createLink({controller: 'accountingReport', action: 'commission'});
              window.open (url+'?dateFrom='+$('#comm_datefrom').val()+'&dateTo='+$('#comm_dateto').val(),"mywindow3","menubar=1,resizable=1,width=730,height=800,scrollbars=1" );


    });


    $('#publisherAds').click(function(){

       var url =g.createLink({controller: 'accountingReport', action: 'agent'});
      window.open (url+'?agentSelection='+$('#agentSelection').val()+'&publisherDateFrom='+$('#publisherDateFrom').val()+'&publisherDateTo='+$('#publisherDateTo').val()+'&div_id='+cancelDiv_ID,"mywindow1","menubar=1,resizable=1,width=950,height=800,scrollbars=1" );

    });

       $('#deferredAds').click(function(){

       var url =g.createLink({controller: 'accountingReport', action: 'deferredAds'});
       window.open (url+'?agentDescID='+$('#agentDescID').val()+'&languageType='+$('#languageType').val()+'&deferredFrom='+$('#deferredFrom').val()+'&deferredTo='+$('#deferredTo').val()+'&adsType='+$('#adsType').val()+'&div_id='+cancelDiv_ID,"mywindow1","menubar=1,resizable=1,width=950,height=800,scrollbars=1" );

    });

  });


  function callCalendar(obj)
  {
$(obj).datepicker({dateFormat:'dd/mm/yy'}).datepicker( "show");
  }


function clear_Fields()
  {
   $("#txt_datefrom").val("");
   $("#txt_dateto").val("");
   $("#txt_clientName").val("");
   $("#ro_id").val("");
   $("#txt_bid").val("");
   $('#commission').hide();
     $("#peid").val("");
     $("#languageid").val("");
     $('#itemCodeTypeId').val("");
     $("#adtypeid").val("");
     $("#adTypeClass1").val("");
     $("#adTypeClass2").val("");
     $("#agencyid").val("");
     $("#status_publisher").val("");
     $("#status_booking").val("");
     $("#status_agent").val("");
     $("#adTypeClass1").empty();
     $("#adTypeClass2").empty();
     $("#ads_type").val("");
  }
 function shw()
   {
 $("#ads_div").hide();
 $('#calendarForDateRange').hide();
 $("#receipt1_div").toggle();
 $("#enterBookingID").val('');
 $('#receipt_div').animate({})
 $('#commission').hide();
 $("#publisher_div").hide();
 $("#deferred_div").hide();
 }
  function shwAds(obj)
  {
 cancelDiv_ID= $(obj).attr("id");
 $("#receipt1_div").hide();
$('#calendarForDateRange').hide();
$("#ads_div").toggle();
$('#commission').hide();
$("#publisher_div").hide()
$("#deferred_div").hide();
  }

  function showMyCal(obj)
 {
 id = $(obj).attr("id");
 $("#paymentTypeID").val(id);
 $("#calendarForDateRange").show();
 $("#ads_div").hide();
 $("#receipt1_div").hide();
 $('#commission').hide();
 $("#publisher_div").hide()
 $("#deferred_div").hide();
 }


   function showCommission()
   {
$("#receipt1_div").hide();
 $("#ads_div").hide();
$('#calendarForDateRange').hide();
$("#commission").toggle();
$("#publisher_div").hide();
$("#deferred_div").hide();
   }


  function showpublisher(obj)
   {
 cancelDiv_ID= $(obj).attr("id");
 $("#receipt1_div").hide();
 $("#ads_div").hide();
$('#calendarForDateRange').hide();
$("#commission").hide();
 $("#publisher_div").toggle();
  $("#deferred_div").hide();
   }

function showdeferred(obj)
   {
 cancelDiv_ID= $(obj).attr("id");
 $("#receipt1_div").hide();
 $("#ads_div").hide();
 $('#calendarForDateRange').hide();
 $("#commission").hide();
 $("#publisher_div").hide();
  $("#deferred_div").toggle();
  }












