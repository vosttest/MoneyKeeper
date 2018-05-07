import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiProvider } from './api';
import { JwtHelper } from 'angular2-jwt';
import { Observable } from 'rxjs/Rx';
import { RsaService } from '../utils';

@Injectable()
export class UserProvider {
    public timerLogout;
    public subscriptionLogout;

    constructor(private api: ApiProvider,
        private rou: Router,
        private rsa: RsaService) { }

    /**
     * Get API URL
     */
    public getURL() {
        return this.api.apiUrl + "/";
    }

    /**
    * Handle error
    * @param error
    */
    public handleError(error: any) {
        return "Error message";
    }

    /**
     * Register
     * @param info
     */
    public register(info: any) {
        info.password = this.rsa.encrypt(info.password); // encrypt password
        return this.api.postz('user/register', info);
    }

    /**
     * Login
     * @param info
     */
    public login(info: any) {
        info.password = this.rsa.encrypt(info.password); // encrypt password
        return this.api.postz('user/login', info);
    }

    /**
     * Logout
     */
    public logout() {
        this.api.get('user/logout').subscribe((rsp: any) => {
        }, err => console.log(err));
        localStorage.removeItem("CURRENT_TOKEN");
        this.rou.navigate(['/']);
    }

    /**
    * Change password
    * @param info
    */
    public changePassword(info: any) {
        info.oldpassword = this.rsa.encrypt(info.oldpassword); // encrypt password
        info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.postz('user/change-password', info);
    }

    /**
     * Reset password
     * @param info
     */
    public resetPassword(info: any) {
        info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.postz('user/reset-password', info);
    }

    /**
     * Forget password
     * @param info
     */
    public forgotPassword(info: any) {
        //info.newpassword = this.rsa.encrypt(info.newpassword); // encrypt password
        return this.api.postz('user/forgot-password', info);
    }

    /**
     * Save user
     * @param info
     */
    public saveUser(info: any) {
        return this.api.postz('user/save', info);
    }

    /**
     * View user
     * @param id
     */
    public viewUser(id: String) {
        return this.api.get('user/view?id=' + id);
    }

    /**
     * Get configure
     */
    public getConfig() {
        return this.api.get('common/config');
    }

    /**
     * Save authentication
     * @param token
     * @param redirect
     */
    public saveAuth(token: string, redirect: boolean = true) {
        let t = this.api.saveToken(token);
        this.timerLogout = Observable.interval(this.api.milliseconds);

        this.subscriptionLogout = this.timerLogout.subscribe(x => {
            let now = new Date();
            if (now > this.api.nextRun && this.api.allowLogout) {
                this.logout();
            }
        });

        if (redirect) {
            this.checkRedirect(t.user.accessrights);
        }
    }

    /**
     * Check access rights
     * @param right
     */
    public checkAccessRights(right: String): boolean {
        let res: any = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        if (res == null) {
            return false;
        }

        if (right === "/dashboard") {
            right = "Dashboard"
        }

        let ok = res.accessRights.find(x => x === right);
        return ok != undefined && ok != "";
    }

    /**
     * Check redirection
     */
    public checkRedirection() {
        let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
        if (user != null) {
            this.checkRedirect(user.accessRights);
        }
    }

    /**
     * Check redirect
     * @param ar Access rights
     */
    private checkRedirect(ar: any) {
        let dashboard = ar.find(myObj => myObj == "Dashboard");
        let report = ar.find(myObj => myObj == "Report");

        if (dashboard != undefined) {
            this.rou.navigate(['/dashboard']);
        }
        else {
            this.rou.navigate(['/error-page']);
        }
    }
}