$(document).ready(function() {


	$('#loginform').bootstrapValidator({
       // container: '#messages',
    	framework : 'bootstrap',
    	live: 'enabled',
    	err: {
            container: function($field, validator) {
                return $field.parent().next('.messageContainer');
            }
        },
       
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            
        	j_username: {
        		validMessage: 'Email looks great',
                validators: {
                    notEmpty: {
                        message: 'Username is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        /*regexp: /\S+@\S+\.\S+/,*/
                        regexp: /^[a-zA-Z0-9\s]+$/,
                        message: 'Username is not valid'
                    }
                    
                }
            },
        	
        	
        	j_password: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    },
                    stringLength: {
                        enabled: true,
                        min: 8,
                        max: 40,
                        message: 'Password must conform to password policy'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[a-zA-Z0-9\s]+$/,
                        message: 'Special characters not allowed'
                    }
                }
            }
            
        }
    })
    	
        $('#signupform').bootstrapValidator({
       // container: '#messages',
    	framework : 'bootstrap',
    	live: 'enabled',
    	err: {
            container: function($field, validator) {
            	return $field.parent().next('.signupMessageContainer');
            }
        },
       
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            
        	email: {
        		
                validators: {
                    notEmpty: {
                        message: 'The email address is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/,
                        message: 'The email address is not valid'
                    }
                    
                }
            },
        	
            firstName: {
        		
                validators: {
                    notEmpty: {
                        message: 'The First name is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[A-z]+$/,
                        message: 'The First Name is not valid'
                    }
                    
                }
            },
        	
            lastName: {
        		
                validators: {
                    notEmpty: {
                        message: 'The Last name is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[A-z]+$/,
                        message: 'The Last name is not valid'
                    }
                    
                }
            },
            
           
            contactNumber: {
        		
                validators: {
                    notEmpty: {
                        message: 'The phone number is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/,
                        message: 'The phone number entered is not valid'
                    }
                    
                }
            },
            
            addrStreetName: {
        		
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[A-Za-z0-9'\.\-\s\,]+$/,
                        message: 'Special characters allowed are . , - '
                    }
                    
                }
            },
            
            addrAptNo: {
        		
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[A-Za-z0-9'\.\-\s\,]+$/,
                        message: 'Special characters allowed are . , - '
                    }
                    
                }
            },
            
            addrCity: {
        		
                validators: {
                    notEmpty: {
                        message: 'The city is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[A-z\s]+$/,
                        message: 'Enter valid city name'
                    }
                    
                }
            },
            
            addrZip: {
        		
                validators: {
                    notEmpty: {
                        message: 'The zipcode is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /(^\d{5}$)|(^\d{5}-\d{4}$)/,
                        message: 'Enter valid zipcode'
                    }
                    
                }
            },
            
            username: {
        		
                validators: {
                    notEmpty: {
                        message: 'The username is required and cannot be empty'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[A-Za-z0-9]+$/,
                        message: 'Special characters not allowed'
                    }
                    
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        enabled: true,
                        min: 8,
                        max: 40,
                        message: 'The password must be more than 8 and less than 40 characters long'
                    },
                    regexp: {
                        enabled: true,
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'The password can only consist of Alphabets and Numbers'
                    },
                    
                }
            },
            confirmpassword: {
                validators: {
                	notEmpty: {
                        message: 'The confirm password is required and cannot be empty'
                    },
                    
                    identical:{
                    	field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            }
        }
    })
    


});


