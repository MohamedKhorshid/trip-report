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
  total: number;

  searchStart: Date;
  searchEnd: Date;

  constructor(private tripService: TripService) {
    this.searchStart = this.createDateOnly();
    this.searchEnd = this.createDateOnly();
    this.searchStart = new Date(this.searchStart.getTime() - (7 * 24 * 3600 * 1000));
  }

  ngOnInit() {
  }

  search() {
    this.tripService.getTrips(this.searchStart, this.searchEnd).then(result => {
      this.trips = result.list;
      this.total = result.total;
    });
  }

  clear() {
    this.trips = [];
  }

  createDateOnly(): Date {
    const date = new Date();

    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);

    return date;
  }

}
