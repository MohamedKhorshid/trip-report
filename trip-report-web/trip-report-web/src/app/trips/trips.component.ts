import { Trip } from './../trip';
import { TripService } from './../trip.service';
import { Component, OnInit } from '@angular/core';
import { __values } from 'tslib';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.css']
})
export class TripsComponent {

  preload = false;

  trips: Trip[] = [];
  total: number;
  current = 1;
  pageSize = 10;

  searchStart: Date;
  searchEnd: Date;

  constructor(private tripService: TripService) {
    if (this.preload) {
      this.searchStart = this.createDateOnly();
      this.searchEnd = this.createDateOnly();
      this.searchStart = new Date(this.searchStart.getTime() - (7 * 24 * 3600 * 1000));
    }
  }

  search(page: number) {
    console.log(page);
    this.trips = [];
    this.tripService.getTrips(this.searchStart, this.searchEnd, page ? page : 1, this.pageSize).then(result => {
      result.list.forEach(trip => {
        this.trips.push(new Trip(trip));
      });
      this.total = result.total;
    });

    this.current = page ? page : 1;
  }

  clear() {
    this.trips = [];
    this.total = null;
    this.current = 1;
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
