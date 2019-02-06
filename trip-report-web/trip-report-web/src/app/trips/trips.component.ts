import { Trip } from './../trip';
import { TripService } from './../trip.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.css']
})
export class TripsComponent implements OnInit {

  trips: Trip[];

  constructor(private tripService: TripService) {}

  ngOnInit() {
    this.getTrips();
  }

  getTrips(): void {
    this.tripService.getTrips().then(trips => this.trips = trips);
    console.log(this.trips);
  }

}
