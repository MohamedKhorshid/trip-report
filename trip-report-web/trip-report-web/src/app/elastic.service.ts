import { Injectable } from '@angular/core';
import { Client } from 'elasticsearch-browser';

@Injectable({
  providedIn: 'root'
})
export class ElasticService {

  private client: Client;

  constructor() {
    if (!this.client) {
      this.client = new Client({
        host: 'http://localhost:9200',
        log: 'trace'
      });
    }
  }

  search(idx: string, typ: string, queryObj: any): Promise<any[]> {

    return this.client.search({
      index: idx,
      type: typ,
      body: {
        query: queryObj
      }, filterPath: ['hits.hits._source', 'hits.hits._id']
    }).then(values => {
      if (values.hits) {
        return values.hits.hits.map((value) => {
          const val = value._source;
          val.id = value._id;
          return val;
        });
      }
    });

  }

  getAll(idx: string, typ: string): Promise<any[]> {
    return this.search(idx, typ, {
      match_all: {}
    });
  }

  searchRange(idx: string, typ: string, property: string, startRange: any, endRange: any): Promise<any[]> {

    return this.search(idx, typ, {
      range: {
        [property]: {
          gte: startRange,
          lte: endRange
        }
      }
  });
  }

  find(idx: string, typ: string, identifier: string): Promise<any> {
    return this.client.get({
      index: idx,
      type: typ,
      id: identifier
    }).then(value => {
        const val = value._source;
        val.id = value._id;
        return val;
    });
  }

}
