import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class CommonProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param type
     */
    public search(type: string) {
        let x = {
            'keyword': type
        };

        return this.api.post('common/search', x);
    }

    /**
     * Search by
     * @param type
     */
    public getImages(info: any) {
        return this.api.post('common/getImages', info);
    }
}