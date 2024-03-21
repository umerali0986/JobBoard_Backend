import { Component, OnInit } from '@angular/core';
import { HomePageService } from 'src/app/Services/home-page.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit{

  constructor(private homeService : HomePageService){}
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


}
