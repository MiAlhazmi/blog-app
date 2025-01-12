import { Component } from '@angular/core';

@Component({
	selector: 'app-page-not-found',
	template: `
		<body>
			<div class="container">
    			<div class="not_found">
					<div class="image-container">
          				<img src="/assets/images/go2.png" alt="Error 404" class="image">
       				</div>
        			<h1>Oops! Error 404</h1>
    			</div>
			</div>
		</body>
	`,
	styles: [`
		* {
			transition: all 0.6s;
			margin: 0;
			padding: 0;
			box-sizing: border-box;
			font-family: 'Poppins', sans-serif;
		}

		html {
			height: 100%;
		}

		body {
			font-family: 'Lato', sans-serif;
			color: #888;
			height: 100vh;
			display: flex;
			justify-content: center;
			align-items: center;
			background: linear-gradient(135deg, #E4E9EC, #8ea1ad);
		}

		.image-container {
     		margin-bottom: 20px;
   	 	}

		.image {
			width: 300px;
			height: auto;
		}

		.not_found{
			display: flex;
      		flex-direction: column;
      		align-items: center;
		}

		.not_found h1{
			font-size: 50px;
			display: inline-block;
			padding-right: 12px;
			animation: type .5s alternate infinite;
		}
		/* .container {
			max-width: 700px;
			width: 100%; 
			background-color: #fff;
			padding: 25px 30px;
			border-radius: 5px;
			box-shadow: 0 0px 30px rgba(0, 0, 0, 0.30);
		} */
		
		@keyframes type {
			from{box-shadow: inset -3px 0px 0px #888;}
			to{box-shadow: inset -3px 0px 0px transparent;}
		}
  `]
})
export class PageNotFoundComponent { }
