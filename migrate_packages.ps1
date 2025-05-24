# Package Migration Script
# This script migrates package structure from com.atguigu.gulimall to com.terenceqin.gulimall

$modules = @(
    "gulimall-coupon",
    "gulimall-member", 
    "gulimall-order",
    "gulimall-ware",
    "gulimall-cart",
    "gulimall-seckill",
    "gulimall-gateway",
    "gulimall-auth-server",
    "gulimall-search",
    "gulimall-third-party",
    "gulimall-common"
)

$testModules = @(
    "mall-test-sso-server",
    "mall-test-sso-clientA", 
    "mall-test-sso-clientB"
)

foreach ($module in $modules) {
    Write-Host "Processing module: $module"
    
    $oldPath = "$module/src/main/java/com/atguigu/gulimall"
    $newPath = "$module/src/main/java/com/terenceqin/gulimall"
    
    if (Test-Path $oldPath) {
        # Create new directory structure
        $newDir = "$module/src/main/java/com/terenceqin/gulimall"
        New-Item -ItemType Directory -Path $newDir -Force | Out-Null
        
        # Get module name from path
        $moduleName = ($module -split "-")[-1]
        if ($module -eq "gulimall-common") {
            # For common module, move all content
            Move-Item -Path "$oldPath/*" -Destination $newDir -Force
        } else {
            # For other modules, create module-specific directory
            $moduleDir = "$newDir/$moduleName"
            New-Item -ItemType Directory -Path $moduleDir -Force | Out-Null
            Move-Item -Path "$oldPath/$moduleName/*" -Destination $moduleDir -Force
        }
        
        # Remove old directory structure
        Remove-Item -Path "$module/src/main/java/com/atguigu" -Recurse -Force
        Write-Host "Migrated: $module"
    }
}

foreach ($module in $testModules) {
    Write-Host "Processing test module: $module"
    
    $oldPath = "$module/src/main/java/com/atguigu/gulimall"
    $newPath = "$module/src/main/java/com/terenceqin/gulimall"
    
    if (Test-Path $oldPath) {
        # Create new directory structure
        New-Item -ItemType Directory -Path $newPath -Force | Out-Null
        
        # Move all content
        Move-Item -Path "$oldPath/*" -Destination $newPath -Force
        
        # Remove old directory structure  
        Remove-Item -Path "$module/src/main/java/com/atguigu" -Recurse -Force
        Write-Host "Migrated: $module"
    }
}

Write-Host "Package migration completed!" 