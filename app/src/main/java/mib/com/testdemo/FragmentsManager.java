package mib.com.testdemo;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;


import mib.com.testdemo.HomeFragment;

import java.util.ArrayList;

import android.content.Context;
import mib.com.testdemo.R;

/**
 * Created by mibihi on 9/13/17.
 */

public class FragmentsManager implements FragmentManager.OnBackStackChangedListener{
    Context context;
    FragmentManager fragmentManager;

    //BackPressListener backPressListener;


    public FragmentsManager(Context paramContext, FragmentManager paramFragmentManager) {
        this.context = paramContext;
        this.fragmentManager = paramFragmentManager;
        this.fragmentManager.addOnBackStackChangedListener(this);
    }

    public void addFragment(Fragment fragment, int containerId, String fragmentName) {
        FragmentTransaction localFragmentTransaction = this.fragmentManager.beginTransaction();
      //  localFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        localFragmentTransaction.add(containerId, fragment, fragmentName).addToBackStack(fragmentName).commit();


    }

    public void addFragment(Fragment fragment, int containerId, String fragmentName, boolean clearStack) {

        if (clearStack) {
            clearStack();
        }

        FragmentTransaction localFragmentTransaction = this.fragmentManager.beginTransaction();
       // localFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        localFragmentTransaction.add(containerId, fragment, fragmentName).addToBackStack(fragmentName).commit();

    }


    public void replaceFragment(Fragment fragment, int containerId, String fragmentName, boolean clearStack) {
        if (clearStack) {
            clearStack();
        }

        FragmentTransaction localFragmentTransaction = this.fragmentManager.beginTransaction();
       // localFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        localFragmentTransaction.replace(containerId, fragment, fragmentName).commit();

    }

    public void popBackFragment() {
        this.fragmentManager.popBackStack();
    }

    public boolean popBackFragment(String name, int flag) {
        return this.fragmentManager.popBackStackImmediate(name, flag);
    }

    public void clearStack() {
        for (int i = 0; i < this.fragmentManager.getBackStackEntryCount(); i++) {
            this.fragmentManager.popBackStackImmediate();
        }
    }


    @Override
    public void onBackStackChanged() {
      /*  Log.d(FragmentsManager.class.getSimpleName(), "back stack count:" + this.fragmentManager.getBackStackEntryCount());
        if (this.fragmentManager.getBackStackEntryCount() >= 1) {
            Methods.toggleActionBarIcon(SKConstants.ActionDrawableState.BURGER, ((MainActivity) this.context).actionBarDrawerToggle, true);
            ((MainActivity) this.context).mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            Methods.toggleActionBarIcon(SKConstants.ActionDrawableState.ARROW, ((MainActivity) this.context).actionBarDrawerToggle, true);
            ((MainActivity) this.context).mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }


        Fragment currentFragment = fragmentManager.findFragmentById(R.id.container);
        if (currentFragment != null && currentFragment instanceof HomeFragment) {
            if (context != null && context instanceof MainActivity) {
                ((MainActivity) context).showOrHideNotificationIndicator();
            }
        } else {
            if (context != null && context instanceof MainActivity) {
                ((MainActivity) context).hideNotificationIndicator();
            }
        }*/

    }


    public void openHomeFragment(boolean addToBackStack, boolean clearBackStack) {
        String fragmentTag = HomeFragment.class.getSimpleName();

        Fragment fragment = new HomeFragment();
        if (addToBackStack) {
            addFragment(fragment, R.id.main_container, fragmentTag, clearBackStack);
        } else {
            replaceFragment(fragment, R.id.main_container, fragmentTag, clearBackStack);
        }
    }

     /*public void reopenHomeFragment(boolean addToBackStack, boolean clearBackStack) {

        String fragmentTag = HomeFragment.class.getSimpleName();
        fragmentManager.popBackStack(fragmentTag, 0);

    }


    public void openCategoryFragment(boolean addToBackStack, boolean clearBackStack, String categoryType) {
        String fragmentTag = CategoryFragment.class.getSimpleName();

        Bundle bundle = new Bundle();
        bundle.putString(SKConstants.KEY_CATEGORY_TYPE, categoryType);

        Fragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openProductListFragment(boolean addToBackStack, boolean clearBackStack, String categoryType, String selectedSubCat, ArrayList<String> subCatList) {
        String fragmentTag = ProductListFragment.class.getSimpleName();

        Bundle bundle = new Bundle();
        bundle.putString(SKConstants.KEY_CATEGORY_TYPE, categoryType);
        bundle.putString(SKConstants.KEY_SUB_CATEGORY_TYPE, selectedSubCat);
        bundle.putStringArrayList(SKConstants.KEY_SUB_CATEGORIES_LIST, subCatList);


        Fragment fragment = new ProductListFragment();
        fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openBrandedProductListFragment(boolean addToBackStack, boolean clearBackStack, String navigation, String categoryType, String selectedSubCat, Brand brand, SKUser skUser) {
        String fragmentTag = BrandedProductListFragment.class.getSimpleName();

        Bundle bundle = new Bundle();
        bundle.putString("navigation", navigation);
        bundle.putString(SKConstants.KEY_CATEGORY_TYPE, categoryType);
        bundle.putString(SKConstants.KEY_SUB_CATEGORY_TYPE, selectedSubCat);
        bundle.putSerializable(Brand.class.getSimpleName(), brand);
        bundle.putSerializable(SKUser.class.getSimpleName(), skUser);

        Fragment fragment = new BrandedProductListFragment();
        fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openProductDetailFragment(boolean addToBackStack, boolean clearBackStack, String navigation, String categoryType, String selectedSubCat) {
        String fragmentTag = ProductDetailsFragment.class.getSimpleName();

        Bundle bundle = new Bundle();
        //bundle.putString(SKConstants.KEY_CATEGORY_TYPE,categoryType);
        //bundle.putString(SKConstants.KEY_SUB_CATEGORY_TYPE,selectedSubCat);
        bundle.putString("navigation", navigation);
        Fragment fragment = new ProductDetailsFragment();
        fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openMostPopularsProductFragment(boolean addToBackStack, boolean clearBackStack) {
        String fragmentTag = MostPopularProductListFragment.class.getSimpleName();

        Fragment fragment = new MostPopularProductListFragment();

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openMostPopularsProductFragment(boolean addToBackStack, boolean clearBackStack, String navigation, String filterQuery, int queryType) {
        String fragmentTag = MostPopularProductListFragment.class.getSimpleName();

        Fragment fragment = new MostPopularProductListFragment();

        Bundle bundle = new Bundle();
        bundle.putString("navigation", navigation);
        bundle.putString("filterQuery", filterQuery);
        bundle.putInt("QueryType", queryType);

        fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openBrandsCategoryFragment(boolean addToBackStack, boolean clearBackStack, String navigation, Brand brand, SKUser skUser, boolean isBranded) {

        String fragmentTag = BrandsCategoryFragment.class.getSimpleName();
        if (fragmentManager.findFragmentByTag(fragmentTag) != null) {
            fragmentManager.popBackStack(fragmentTag, 0);

        } else {


            Fragment fragment = new BrandsCategoryFragment();

            Bundle bundle = new Bundle();
            bundle.putString("navigation", navigation);
            bundle.putBoolean("isBranded", isBranded);
            bundle.putSerializable(Brand.class.getSimpleName(), brand);
            bundle.putSerializable(SKUser.class.getSimpleName(), skUser);
            fragment.setArguments(bundle);

            if (addToBackStack) {
                addFragment(fragment, R.id.container, fragmentTag);
            } else {
                replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
            }
        }
    }


    public void openOrderSummaryFragment(boolean addToBackStack, boolean clearBackStack, boolean reopen) {
        if (!reopen && !(fragmentManager.findFragmentById(R.id.container) instanceof OrderSummaryFragment)) {
            String fragmentTag = OrderSummaryFragment.class.getSimpleName();
            Fragment fragment = new OrderSummaryFragment();

            if (addToBackStack) {
                addFragment(fragment, R.id.container, fragmentTag);
            } else {
                replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
            }
        } else if (reopen) {
            fragmentManager.popBackStack(OrderSummaryFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            String fragmentTag = OrderSummaryFragment.class.getSimpleName();
            Fragment fragment = new OrderSummaryFragment();

            if (addToBackStack) {
                addFragment(fragment, R.id.container, fragmentTag);
            } else {
                replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
            }

        }


    }


    public void openAddToCartonFragment(boolean addToBackStack, boolean clearBackStack, String navigation, String selectedProductId, ArrayList<String> selectedColors) {
        String fragmentTag = AddToCartonFragment.class.getSimpleName();

        //System.out.println("colors:"+selectedColors);
        Bundle bundle = new Bundle();

        bundle.putString("navigation", navigation);
        bundle.putString(SKConstants.KEY_SELECTED_PRODUCT_ID, selectedProductId);
        bundle.putStringArrayList(SKConstants.KEY_SELECTED_COLORS_LIST, selectedColors);

        Fragment fragment = new AddToCartonFragment();
        fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openMembersFragment(boolean addToBackStack, boolean clearBackStack) {
        String fragmentTag = FreeTradeMembersFragment.class.getSimpleName();
        Fragment fragment = new FreeTradeMembersFragment();

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void openEditCartonFragment(boolean addToBackStack, boolean clearBackStack, Bundle bundle) {
        String fragmentTag = EditCartonFragment.class.getSimpleName();

        *//*Bundle bundle=new Bundle();
        bundle.putString(SKConstants.KEY_SELECTED_VARIANT_ID,selectedVariantId);
*//*
        Fragment fragment = new EditCartonFragment();
        if (bundle != null)
            fragment.setArguments(bundle);

        if (addToBackStack) {
            addFragment(fragment, R.id.container, fragmentTag);
        } else {
            replaceFragment(fragment, R.id.container, fragmentTag, clearBackStack);
        }
    }


    public void continueShopping() {

        if (fragmentManager.findFragmentByTag(MostPopularProductListFragment.class.getSimpleName()) != null) {
            fragmentManager.popBackStack(MostPopularProductListFragment.class.getSimpleName(), 0);

        } else if (fragmentManager.findFragmentByTag(CollectionListFragment.class.getSimpleName()) != null) {
            fragmentManager.popBackStack(CollectionListFragment.class.getSimpleName(), 0);

        } else if (fragmentManager.findFragmentByTag(ProductListFragment.class.getSimpleName()) != null) {
            fragmentManager.popBackStack(ProductListFragment.class.getSimpleName(), 0);
        } else if (fragmentManager.findFragmentByTag(ProductDetailsFragment.class.getSimpleName()) != null) {
            fragmentManager.popBackStack(ProductDetailsFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            fragmentManager.popBackStack();
        }

    }
*/

}
