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

  search(idx: string, typ: string, queryObj: any, page: number, siz: number): Promise<any> {

    return this.client.search({
      index: idx,
      type: typ,
      body: {
        query: queryObj,
        size: siz,
        from: ((page - 1) * siz) + 1
      }, filterPath: ['hits.hits._source', 'hits.hits._id', 'hits.total']
    }).then(values => {

      const hits = {total: Number, list: []};
      hits.total = values.hits.total;

      if (values.hits.hits) {
        hits.list = values.hits.hits.map((value) => {
          const val = value._source;
          val.id = value._id;
          return val;
        });

      }
      return hits;
    });

  }

  getAll(idx: string, typ: string): Promise<any> {
    return this.search(idx, typ, {
      match_all: {}
    }, 1, 1);
  }

  searchRange(idx: string, typ: string, property: string, startRange: any, endRange: any, page: number, size: number): Promise<any> {

    return this.search(idx, typ, {
      range: {
        [property]: {
          gte: startRange,
          lte: endRange
        }
      }
  }, page, size);
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
