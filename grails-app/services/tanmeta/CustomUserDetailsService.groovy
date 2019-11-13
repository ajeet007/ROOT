package tanmeta

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import com.tan.User
import org.springframework.web.context.request.RequestContextHolder


class CustomUserDetailsService  {


    /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least one role, so
     * we give a user with no granted roles this one which gets past that restriction but
     * doesn't grant anything.
     */
     static final List NO_ROLES =
             [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

     UserDetails loadUserByUsername(String username, boolean loadRoles)
             throws UsernameNotFoundException {
         return loadUserByUsername(username)
     }

     UserDetails loadUserByUsername(String username)
             throws UsernameNotFoundException {
     User.withTransaction { status ->
         //User user = User.findByLoginName(username)

         def user =User.findByUsername(username)
          println("user instance is"+user)


             if (!user) {
                 throw new UsernameNotFoundException('User not found',
                     username)
             }


             def authorities = user.authorities.collect {
                 new GrantedAuthorityImpl(it.authority)
             }

             return new GrailsUser(user.username,
                     user.password,
                     user.enabled,
                     !user.accountExpired,
                     !user.passwordExpired,
                     !user.accountLocked,
                     authorities ?: NO_ROLES,
                     user.id)
         }

     }
}
