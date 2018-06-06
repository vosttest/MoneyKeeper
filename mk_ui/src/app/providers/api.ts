import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { config, environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { JwtHelper } from 'angular2-jwt';

/**
 * API is a generic REST Api handler. Set your API url first.
 */
@Injectable()
export class ApiProvider {
    public apiUrl = '';
    public imgUrl = '';

    public allowLogout = true;
    public nextRun: Date;
    public milliseconds = 0;

    constructor(private http: HttpClient, private rou: Router) {
        if (environment.production) {
            let tmp = !config.apiUrl.startsWith(location.origin) ? location.origin : '';
            this.apiUrl = tmp + config.apiUrl;

            tmp = !config.imgUrl.startsWith(location.origin) ? location.origin : '';
            this.imgUrl = tmp + config.imgUrl;
        }
        else {
            this.apiUrl = config.apiUrl;
            this.imgUrl = config.imgUrl;
        }
    }

    public getx(endpoint: string): Observable<any> {
        return this.http.get(endpoint);
    }

    public get(endpoint: string, params?: any, reqOpts?: any) {
        if (!reqOpts) {
            reqOpts = {
                headers: new HttpHeaders().set('Authorization', this.getToken()),
                params: new HttpParams()
            };
        }

        // Support easy query params for GET requests
        if (params) {
            reqOpts.params = new HttpParams();
            for (let k in params) {
                reqOpts.params.set(k, params[k]);
            }
        }

        return this.http.get(this.apiUrl + endpoint, reqOpts);
    }

    public post(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders().set('Authorization', this.getToken())
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.post(this.apiUrl + endpoint, body, reqOpts);
    }

    public put(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders().set('Authorization', this.getToken())
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.put(this.apiUrl + endpoint, body, reqOpts);
    }

    public delete(endpoint: string, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders().set('Authorization', this.getToken())
            reqOpts = { headers: h };
        }

        return this.http.delete(this.apiUrl + endpoint, reqOpts);
    }

    public patch(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders().set('Authorization', this.getToken())
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.patch(this.apiUrl + endpoint, body, reqOpts);
    }

    public getUserId(): string {
        let t = localStorage.getItem('CURRENT_TOKEN');
        let json = JSON.parse(t);

        if (json === null) {
            t = "";
        } else {
            t = json.userId;
        }

        return t;
    }

    public download(token: string, params?: any, reqOpts?: any): Observable<any> {
        if (!reqOpts) {
            reqOpts = {
                headers: new HttpHeaders().set('Authorization', this.getToken()),
                responseType: 'blob'
            };
        }
        return this.http.get(this.apiUrl + token + '/' + 'download', reqOpts);
    }

    public saveToken(token: string) {
        let jwt = new JwtHelper();
        let t = jwt.decodeToken(token);

        let user: any = {
            token: token,
            userId: t.user.id,
            firstName: t.user.firstname,
            lastName: t.user.lastname,
            email: t.user.email,
            kycStatus: t.user.kycstatus,
            status: t.user.status,
            accessRights: t.user.accessrights
        };

        localStorage.removeItem('CURRENT_TOKEN');
        localStorage.setItem('CURRENT_TOKEN', JSON.stringify(user));

        // Check token is expired will redirect to sign in page
        let seconds = t.exp - t.iat;
        let beforeLogout = 20;
        let beforeRefresh = beforeLogout + 20;
        let now = new Date();

        let secs = now.getSeconds() + seconds - beforeRefresh;
        this.nextRun = new Date(now.setSeconds(secs));

        let interval = seconds - beforeLogout;
        if (interval <= 0) {
            interval = 40; // call after 40s
        }
        this.milliseconds = interval * 1000; // convert to milliseconds

        return t;
    }

    private getTokeny(): string {
        let t = localStorage.getItem('CURRENT_TOKEN');
        let json = JSON.parse(t);

        if (json === null) {
            t = '';
        } else {
            t = 'Bearer ' + json.token;
            let jwt = new JwtHelper();
            let ok = jwt.isTokenExpired(json.token);
            if (ok) {
                this.rou.navigate(['/']);
            }
        }

        return t;
    }

    private posty(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders().set('Authorization', this.getTokeny())
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.post(this.apiUrl + endpoint, body, reqOpts);
    }

    private getToken(): string {
        let res = localStorage.getItem('CURRENT_TOKEN');
        let json = JSON.parse(res);

        if (json === null) {
            res = "";
        } else {
            let jwt = new JwtHelper();
            let ok = jwt.isTokenExpired(json.token);

            if (ok) {
                localStorage.removeItem('CURRENT_TOKEN');
                this.rou.navigate(['/']);
            }

            res = 'Bearer ' + json.token;
            let now = new Date();

            if (now > this.nextRun) {
                this.allowLogout = false;
                let info = { 'token': json.token };

                this.posty('user/refresh-token', info).subscribe((rsp: any) => {
                    if (rsp.status === 'success') {
                        this.saveToken(rsp.authtoken);
                    }
                    else {
                        console.log(rsp.message);
                    }
                }, err => console.log(err));
            } else {
                this.allowLogout = true;
            }
        }

        return res;
    }
}