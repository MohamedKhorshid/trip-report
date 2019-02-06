import { Component, OnInit, Input } from '@angular/core';
import { Trip } from '../trip';
import { ActivatedRoute } from '@angular/router';
import { TripService } from '../trip.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-trip-detail',
  templateUrl: './trip-detail.component.html',
  styleUrls: ['./trip-detail.component.css']
})
export class TripDetailComponent implements OnInit {

  trip: Trip;

  constructor(
    private route: ActivatedRoute,
    private tripService: TripService,
    private location: Location
    ) { }

  ngOnInit() {
    this.getTrip();
  }

  getTrip() {
    const id = this.route.snapshot.paramMap.get('id');
    this.tripService.getTrip(id).then(trip => this.trip = trip);
  }

  goBack() {
    this.location.back();
  }

}
