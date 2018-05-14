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

    /**
     * Get By Id
     */
    public getById(id: any) {
        return this.api.get('income/getById/' + id);
    }

    /**
     * Add, Edit
     */
    public save(info: any) {
        return this.api.post('income/save/', info);
    }

    /**
     * Delete
     */
    public delete(id: any) {
        return this.api.delete('income/delete/' + id);
    }
}   
