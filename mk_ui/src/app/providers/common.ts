import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class CommonProvider {
    constructor(private api: ApiProvider) { }

    /**
    * Handle error
    * @param error
    */
    public handleError(error: any) {
        return 'Error message';
    }

    /**
     * Get list common
     */
    public search(type: string) {
        let x = {
            'keyword': type
        };

        return this.api.post('common/search', x);
    }
}