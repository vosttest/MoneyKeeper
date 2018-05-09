import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ExpenseProvider {
    constructor(private api: ApiProvider) { }

    /**
    * Handle error
    * @param error
    */
    public handleError(error: any) {
        return 'Error message';
    }

    /**
     * Search
     */
    public search() {
        return this.api.get('expense/search');
    }
}