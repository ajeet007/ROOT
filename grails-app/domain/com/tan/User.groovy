package com.tan

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    Date logindate=new Date();
    String loginName
    Role role


	static constraints = {

        username blank: false,nullable: false
		password blank: false
        logindate (default:new Date())

	}

	static mapping = {


        role column: 'role_id'
		password column: '`password`'
        id column:'user_id'
        username column: 'LoginName'
        loginName column: 'UserName'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		//password = springSecurityService.encodePassword(password)
	}
}


