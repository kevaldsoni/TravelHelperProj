<section id="footer">
	<footer>
		<div class="row sm-footer" style="padding: 20px;">
			<div class="container">
				<div class="col-md-4 col-sm-4 footer-about-box">
					<h4>TRAVEL HELPER</h4>
					<p>Travel Application can be for deciding on best travel mode and scheduling travel.</p>
				</div>
				<div class="col-md-4 col-sm-4 contact-box">
					<h4>CONTACT US</h4>
					<p><i class="fa fa-home"></i> Long Beach CA</p>
					<p><i class="fa fa-envelope-o"></i> email@travelhelper.com</p>
					<p><i class="fa fa-phone"></i> +1 123-456-7890</p>
				</div>
				<div class="clearfix visible-sm-block"></div>
				
				<div class="col-md-4 col-sm-4 footer-subscribe">
					<h4>Social Presence</h4>
					<p>Don't miss any update.</p>
					<p class="social-media">
						<a href="#"><i class="fa fa-facebook"></i></a>
						<a href="#"><i class="fa fa-twitter"></i></a>
						<a href="#"><i class="fa fa-google-plus"></i></a>
						<a href="#"><i class="fa fa-instagram"></i></a>
					</p>
				</div>
			</div>
		</div>
		<!-- <div class="clearfix"></div> -->
		<div class="row sm-footer-nav text-center">
			<!-- <p>
				<a href="#">HOME</a>
				<a href="#">CARS</a>
				<a href="#">SERVICE</a>
				<a href="#">GALLERY</a>
			</p> -->
			<p class="copyright">
				&copy;	2017 TRAVELHELPER ALL RIGHTS RESERVED
			</p>
			<div class="go-up">
				<a href="#"><i class="fa fa-arrow-up"></i></a>
			</div>
		</div>
	</footer>
</section>
<!-- END: FOOTER -->

</div>
<!-- END: SITE-WRAPPER -->
<!-- Load Scripts -->
<script src="/TravelHelper/assets/js/respond.js"></script>
<script src="/TravelHelper/assets/js/jquery.js"></script>
<script src="/TravelHelper/assets/plugins/owl.carousel.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/TravelHelper/assets/js/jquery-ui.min.js"></script>
<script src="/TravelHelper/assets/js/bootstrap-select.min.js"></script>
<script src="/TravelHelper/assets/plugins/wow.min.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/plugins/supersized.3.1.3.min.js"></script>
<script src="/TravelHelper/assets/js/js.js"></script>
<script type="text/javascript">
function initAutocomplete() {
	
	var options = {types: ['geocode']};
	var sourceinput = document.getElementById('autocomplete');
	autocomplete = new google.maps.places.Autocomplete(sourceinput,options);
    autocomplete.addListener('place_changed', fetchGeoCodes);
    
    var destinput = document.getElementById('autocompleteDest');
    destcomplete = new google.maps.places.Autocomplete(destinput,options);
    destcomplete.addListener('place_changed', fetchDestGeoCodes);
 }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCin3LHSQnBxk5b1uCGbnzC5dT8uL93JRw&libraries=places&callback=initAutocomplete"
        async defer></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/parent.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/metro.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/uberFunctions.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/lyftFunctions.js"></script>    
<script type="text/javascript">  
			
			jQuery(function($){
				"use strict";
				$.supersized({
				
					//Functionality
					slideshow               :   1,		//Slideshow on/off
					autoplay				:	1,		//Slideshow starts playing automatically
					start_slide             :   1,		//Start slide (0 is random)
					random					: 	0,		//Randomize slide order (Ignores start slide)
					slide_interval          :   10000,	//Length between transitions
					transition              :   1, 		//0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
					transition_speed		:	500,	//Speed of transition
					new_window				:	1,		//Image links open in new window/tab
					pause_hover             :   0,		//Pause slideshow on hover
					keyboard_nav            :   0,		//Keyboard navigation on/off
					performance				:	1,		//0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)
					image_protect			:	1,		//Disables image dragging and right click with Javascript

					//Size & Position
					min_width		        :   0,		//Min width allowed (in pixels)
					min_height		        :   0,		//Min height allowed (in pixels)
					vertical_center         :   1,		//Vertically center background
					horizontal_center       :   1,		//Horizontally center background
					fit_portrait         	:   1,		//Portrait images will not exceed browser height
					fit_landscape			:   0,		//Landscape images will not exceed browser width
					
					//Components
					navigation              :   1,		//Slideshow controls on/off
					thumbnail_navigation    :   1,		//Thumbnail navigation
					slide_counter           :   1,		//Display slide numbers
					slide_captions          :   1,		//Slide caption (Pull from "title" in slides array)
					slides 					:  	[		//Slideshow Images 
														{image : 'assets/images/car-slide1.jpg', title : 'Slide 2'},
														{image : 'assets/images/car-slide2.jpg', title : 'Slide 1'}
												]
												
				}); 
		    });
		    
</script>