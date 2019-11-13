<%@ page import="com.tan.User" %>
 <div id="footer" style="font-size:medium; border-top:solid 1px #ccc;border-bottom:solid 1px #ccc;background-color: #3E567D;">
      <table height="26px" style="width:100%" cellpadding="0" cellspacing="0">
      <tr>
      <td align="left" style="width:0.5%; background-position:left top;background-color: #3E567D;">
       </td>    <td align="left" style="width:81%; background-position:left top; background-color: #3E567D;"><span style="font-family: sans-serif; font-weight: bold">Welcome :</span><span style="font-family: Georgia; color: #D7EBFF">tanZilla [Version-<g:meta name="app.version"/>] ${session.user?.username}</span></td>
      %{--</td><td align="left" style="width:81%; background-position:left top; background-image: url('../images/mf.png'); background-repeat:repeat"><span style="font-family: sans-serif; font-weight: bold">Welcome :</span><span style="font-family: Georgia; color: #D7EBFF">${session.user?.username}</span></td>--}%
      <td align="right" style="width:15%; background-position:left top;background-color: #3E567D;"><span style="font-family: sans-serif; color: #FF9933;">TANGERINE</span></td>
      <td align="right" style="width:2%; background-position:left top;background-color: #3E567D;"><span style="font-family: sans-serif; color: #D7EBFF">
       <img src="../images/ArowR.gif" /> </span></td>
      <td align="left" style="width:1%; background-position:left top; background-color: #3E567D;">
      </tr></table>
   </div>
