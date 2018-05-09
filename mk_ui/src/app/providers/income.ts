import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class IncomeProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search
     */
    public search() {
        return this.api.get('income/search')
    }
}