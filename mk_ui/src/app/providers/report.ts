import { Injectable } from '@angular/core';
import 'rxjs/add/operator/toPromise';
import { ApiProvider } from './api';

@Injectable()
export class ReportProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Get Report by accountId , fromDate, toDate
     *
     */
    public report(info: any) {
        return this.api.post('report/search/', info);
    }
}